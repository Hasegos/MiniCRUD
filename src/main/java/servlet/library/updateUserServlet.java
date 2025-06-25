package servlet.library;

import dao.library.MemberDAO;
import dto.library.Member;
import util.SecurityUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/Library/update")
public class updateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member loginUser = (Member)session.getAttribute("loginUser");

        if(loginUser == null){
            resp.sendRedirect(req.getContextPath() + "/Library/login");
            return;
        }
        try{
            req.setAttribute("member",loginUser);
            /* CSRF 토큰 생성 */
            String csrfToken = SecurityUtil.generateToken();
            session.setAttribute("csrfToken",csrfToken);

            req.setAttribute("currentPage", "updatePage");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/Library/update.jsp");
            dispatcher.forward(req,resp);
        }
        catch (Exception e){
            e.printStackTrace();
            req.setAttribute("errorMessage","예상치 못한 에러가발생했습니다.");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }
}