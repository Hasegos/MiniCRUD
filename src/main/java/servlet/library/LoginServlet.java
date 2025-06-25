package servlet.library;

import dao.library.MemberDAO;
import dto.library.Member;
import util.SecurityUtil;
import validator.MemberValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Library/loginPost")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession httpSession = req.getSession();
        String sessionToken = (String)httpSession.getAttribute("csrfToken");
        String requestToken = req.getParameter("csrfToken");

        if(!sessionToken.equals(requestToken)){
            req.setAttribute("errorMessage","잘못된 요청입니다");
            resp.sendRedirect(req.getContextPath() + "/Library/login");
            return;
        }

        String email = SecurityUtil.sanitizeInput(req.getParameter("email"));
        String password = SecurityUtil.sanitizeInput(req.getParameter("password"));

        /* error 발생시 */
        List<String> error = MemberValidator.validateMember(email,password);
        if(!error.isEmpty()){
            req.setAttribute("errorMessage",error);
            req.setAttribute("email",email);
            req.setAttribute("password",password);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/login.jsp");
            dispatcher.forward(req,resp);
        }

        try{
            MemberDAO dao = MemberDAO.getInstance();
            Member m = dao.getMemberByEmail(email);

            /* 서로 비번이 같을 때만 통과 */
            if (m != null && m.getPassword().equals(password)) {

                httpSession.setAttribute("name", m.getName());
                httpSession.setAttribute("email", m.getEmail());
                httpSession.setAttribute("loginUser", m);

                /* 아무런 요청없이 1분동안 생존 */
                httpSession.setMaxInactiveInterval(60);

                resp.sendRedirect("index.jsp");
            }
            else{
                req.setAttribute("errorMessage", "비밀번호 또는 아이디를 확인해주세요.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/login.jsp");
                dispatcher.forward(req,resp);
            }

        }catch (SQLException e){
            e.printStackTrace();
            req.setAttribute("errorMessage","비밀번호 또는 아이디를 확인해주세요.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/login.jsp");
            dispatcher.forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorMessage","예상치 못한 오류가 발생했습니다.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/error.jsp");
            dispatcher.forward(req,resp);
        }
    }
}