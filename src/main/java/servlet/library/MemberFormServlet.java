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

@WebServlet("/Library/member")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        String csrfToken = SecurityUtil.generateToken();
        session.setAttribute("csrfToken",csrfToken);

        req.setAttribute("currentPage","MemberPage");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/member.jsp");
        dispatcher.forward(req,resp);
    }
}