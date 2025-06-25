package servlet.Login;

import dao.Login.MemberShipDAO;
import dto.login.Membership;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        MemberShipDAO dao = new MemberShipDAO();
        Membership membership = dao.getMemberByEmail(email);
        
        // 로그인 성공
        if(membership != null && membership.getPassword().equals(password)){

            HttpSession httpSession = req.getSession();

            httpSession.setAttribute("userName", membership.getName());
            httpSession.setAttribute("userEmail", membership.getEmail());

            resp.sendRedirect("main.jsp");
        }
        else{
            req.setAttribute("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}