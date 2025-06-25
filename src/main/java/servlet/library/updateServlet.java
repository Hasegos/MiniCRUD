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

@WebServlet("/Library/updatePost")
public class updateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String csrfToken = (String)session.getAttribute("csrfToken");
        String requestToken = req.getParameter("csrfToken");

        if(session == null || csrfToken == null || requestToken == null || !csrfToken.equals(requestToken)){
            req.setAttribute("errorMessage", "잘못된 요청입니다.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/error.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        /* 로그인 된 회원 정보 */
        Member loginMember = (Member)session.getAttribute("loginUser");
        int memberId = loginMember.getMember_id();

        /* 입력된 회원 정보 */
        String name = req.getParameter("name");
        String email = SecurityUtil.sanitizeInput(req.getParameter("email"));
        String password = SecurityUtil.sanitizeInput(req.getParameter("password"));

        /* error 발생시 */
        List<String> error = MemberValidator.validateMember(email,password);
        if(!error.isEmpty()){
            req.setAttribute("errorMessage",error);
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.getRequestDispatcher("/Library/update.jsp").forward(req,resp);
            return;
        }

        try{
            MemberDAO dao = MemberDAO.getInstance();
            Member m = new Member();
            m.setMember_id(memberId);
            m.setName(name);
            m.setEmail(email);
            m.setPassword(password);

            boolean result = dao.updateMember(m);

            if(result) {
                session.setAttribute("loginUser",m);
                session.setAttribute("name",name);
                session.setMaxInactiveInterval(60);

                resp.sendRedirect(req.getContextPath() + "/Library/index.jsp");
            }else {
                req.setAttribute("errorMessage", "회원정보 수정에 실패했습니다.");
                req.getRequestDispatcher("/Library/update.jsp").forward(req, resp);
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