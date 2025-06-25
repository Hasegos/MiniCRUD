package dao.Login;

import util.DBConnection;
import dto.login.Membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberShipDAO {

    /* 새로운 회원가입 할때 */
    public int insertNumber(Membership m){
        String checkSql = "SELECT COUNT(*) FROM memberShip WHERE email = ?";
        String insertQql = "INSERT INTO memberShip (name, email, password) values (?, ?, ?)";
        int result = 0;

        try(Connection conn = DBConnection.getConnection()){
            /* 이메일 기준으로 중복 처리 */
            try(PreparedStatement checkPstmt = conn.prepareStatement(checkSql)) {
                checkPstmt.setString(1, m.getEmail());

                ResultSet rs = checkPstmt.executeQuery();
                if(rs.next() && rs.getInt(1) > 0){
                    System.out.println("이미 등록된 이메일입니다.");
                    return 0;
                }
            }
            /* 새로운 데이터 삽입 */
            try(PreparedStatement insertPstmt = conn.prepareStatement(insertQql)) {
                insertPstmt.setString(1, m.getName());
                insertPstmt.setString(2, m.getEmail());
                insertPstmt.setString(3, m.getPassword());

                result = insertPstmt.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /* email 를 통한 값으로 찾기 */
    public Membership getMemberByEmail(String email){
        String sql = "SELECT * FROM memberShip where email = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                Membership m = new Membership();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));

                return m;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        // 로그인 정보가 없을때
        return null;
    }

    public List<Membership> getAllMembers(){
        List<Membership> list = new ArrayList<>();
        String sql = "SELECT * FROM memberShip";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Membership m = new Membership();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));

                list.add(m);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}