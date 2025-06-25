package servlet.library;


import util.SecurityUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Library/login")
public class LoginFromServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /* CSRF 토큰 생성 */
        HttpSession session = req.getSession();

        /* 이미 로그인 되어있는 경우*/
        if(session.getAttribute("loginUser") != null){
            resp.sendRedirect(req.getContextPath() + "/Library/index.jsp");
            return;
        }
        /* CSRF 토큰 생성 */
        String csrfToken = SecurityUtil.generateToken();
        session.setAttribute("csrfToken", csrfToken);

        req.setAttribute("currentPage", "LoginPage");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/login.jsp");
        dispatcher.forward(req,resp);
    }
}
