package dao.SolvedAC;

import util.DBConnection;
import dto.solvedAC.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    
    /* DB에 새로운 데이터 추가를 위한 메서드 */
    public int insertMember(Member m){
        String sql = "INSERT INTO member (name,solvedCount,class,joinedAt) Values (?,?,?,?)";
        int result = 0;
        try (Connection conn = DBConnection.getConnection();
             /* PreparedStatement : SQL Injection 방어 (컴파일을 미리 해서 깨지는걸 방지) */
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1,m.getName());
             pstmt.setInt(2,m.getSolvedCount());
             pstmt.setInt(3,m.getclass());
             pstmt.setString(4, m.getJoinedAt());

             result = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    /* DB 전체 조회기능 */
    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setSolvedCount(rs.getInt("solvedCount"));
                m.setClass(rs.getInt("Class"));
                m.setJoinedAt(rs.getString("joinedAt"));

                list.add(m);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}