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
import java.util.List;


// 회원 가입 서블릿
@WebServlet("/Login/membership")
public class MemberShipServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name =req.getParameter("name");
        String email =req.getParameter("email");
        String password =req.getParameter("password");

        Membership m = new Membership();
        m.setName(name);
        m.setEmail(email);
        m.setPassword(password);

        /* DAO 를 통한 DB에 데이터 넣기 */
        MemberShipDAO memberShipDAO = new MemberShipDAO();
        int result = memberShipDAO.insertNumber(m);

        List<Membership> getAllMembers = memberShipDAO.getAllMembers();

        if(result > 0){
            // session 저장
            HttpSession session = req.getSession();
            session.setAttribute("userName",m.getName());
            session.setAttribute("userEmail",m.getEmail());
            session.setAttribute("getAllMembers", getAllMembers);

            resp.sendRedirect("main.jsp");
        }
        else{
            req.setAttribute("error", "회원가입이 실패했습니다.");
            req.getRequestDispatcher("membership.jsp").forward(req,resp);
        }
    }
}
