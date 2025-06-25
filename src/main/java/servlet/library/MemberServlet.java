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

@WebServlet("/Library/memberPost")
public class MemberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String sessionToken = (String)session.getAttribute("csrfToken");
        String requestToken = req.getParameter("csrfToken");

        if(!sessionToken.equals(requestToken)){
            req.setAttribute("errorMessage","잘못된 요청입니다.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/error.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        String name = req.getParameter("name");
        String email = SecurityUtil.sanitizeInput(req.getParameter("email"));
        String password = SecurityUtil.sanitizeInput(req.getParameter("password"));

        List<String> error = MemberValidator.validateMember(email,password);

        if(!error.isEmpty()){
            req.setAttribute("errorMessage",error);
            req.setAttribute("name", name);
            req.setAttribute("email" , email);
            req.setAttribute("password",password);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/member.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        try{
            MemberDAO dao = MemberDAO.getInstance();
            Member m = dao.getMemberByEmail(email);

            if(m != null){
                session.setAttribute("errorMessage","이미 존재하는 회원입니다.");
                session.setAttribute("name", name);
                session.setAttribute("email", email);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/member.jsp");
                dispatcher.forward(req,resp);
                return;
            }

            Member newMember = new Member();
            newMember.setName(name);
            newMember.setEmail(email);
            newMember.setPassword(password);


            int result = dao.insertMember(newMember);

            /* 회원가입 성공 */
            if(result > 0){
                // 로그인쪽으로 보내기
                req.setAttribute("successMessage","회원가입이 완료되었습니다.");
                req.getRequestDispatcher("/Library/login.jsp").forward(req,resp);
            }

        }catch (SQLException e){
            e.printStackTrace();
            req.setAttribute("errorMessage","회원 가입이 실패했습니다.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/member.jsp");
            dispatcher.forward(req,resp);

        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorMessage","예상치 못한 에러가발생했습니다.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/error.jsp");
            dispatcher.forward(req,resp);
        }
    }
}