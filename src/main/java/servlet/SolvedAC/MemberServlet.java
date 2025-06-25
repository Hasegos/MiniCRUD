package servlet.SolvedAC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import dao.SolvedAC.MemberDAO;
import dto.solvedAC.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@WebServlet("/SolvedAC/member")
public class MemberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");

        // Solved AC API
        String url = "https://solved.ac/api/v3/search/user?query=" + name;
        try {
            User list = solvedacAPIRequest(url).items.get(0);
            int solvedCount = list.solvedCount;
            String joinedAt = list.joinedAt;
            int Class = list.userClass;

            Member m = new Member();
            m.setName(name);
            m.setSolvedCount(solvedCount);
            m.setJoinedAt(joinedAt);
            m.setClass(Class);
            
            // DAO 의 저장기능 활용 -> DB 저장
            MemberDAO memberDAO = new MemberDAO();
            int result = memberDAO.insertMember(m);

            List<Member> getAllMembers = memberDAO.getAllMembers();
            
            // 서버 내 저장
            req.setAttribute("result", result);
            req.setAttribute("name", name);
            req.setAttribute("solvedCount", solvedCount);
            req.setAttribute("joinedAt", joinedAt);
            req.setAttribute("Class", Class);
            req.setAttribute("getAllMembers", getAllMembers);

            RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
            dispatcher.forward(req,resp);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // API 를 이용한 유저 정보
    public static SearchResult solvedacAPIRequest(String url) throws IOException, InterruptedException{
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-solvedac-language", "ko")
                .header("Accept","application/json")
                .GET()
                .build();

        /*
        * HttpClient.newHttpClient() 는 간단히 HTTP 클래스 새 인스턴스를  만드는부분이죠.
        * 간단하게는 HTPP 연결하려고 설정 되어있는 클라이언트를 만든다고 생각 하시면 되요
        *
        * .send(httpRequest, HttpResponse.BodyHandlers.ofString())
        * .send() 얘는  httpRequest 를 서버로 전송하고나서 받은 응답을 처리하는애에요
        * 참고로 반환은 HttpResponst<T> 로 반환하구요
        * HttpResponse.BodyHandlers.ofString() 얘는 .toString 을 보시면 아시겠지만 문자열로 받겠다는거구요
        *
        * 정리하면 만든 httpRequest를 서버로 보내고, 결과 본문은 문자열로 읽어서 HttpResponse로 돌려줘.
        *
        *
        *
        * */
        HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // Gson 형태로
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        SearchResult json =  gson.fromJson(response.body(), SearchResult.class);

        return json;
    }
}

// User 에 대한 DTO
class User{
    String handle; // 이름
    int solvedCount; // 문제 푼수
    @SerializedName("class")
    int userClass; // 클래스 단계
    String joinedAt; // 가입일
}

// List 타입으로 반환
class SearchResult{
    List<User> items;
}