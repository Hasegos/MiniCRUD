package dao.library;

import util.DBConnection;
import dto.library.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

    /* 싱글톤 패턴 */
    private static MemberDAO instance = new MemberDAO();

    /* 기본 생성자 */
    private MemberDAO(){}

    public static MemberDAO getInstance(){
        return instance;
    }

    /* 새로운 회원 가입 */
    public int insertMember(Member m) throws SQLException{
        String checkSQL = "SELECT COUNT(*) FROM member WHERE email = ?";
        String sql = "INSERT INTO Member (name, email, password) VALUES (?,?,?)";
        int result = 0;

        try(Connection conn = DBConnection.getConnection()) {

            /* 이메일 기준으로 중복 처리 */
            try (PreparedStatement pstmt = conn.prepareStatement(checkSQL)) {
                pstmt.setString(1, m.getEmail());

                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("이미 등록된 회원입니다.");
                    return 0;
                }
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, m.getName());
                pstmt.setString(2, m.getEmail());
                pstmt.setString(3, m.getPassword());

                result = pstmt.executeUpdate();
            }
        }
        return result;
    }

    /* email 기준으로 찾기 */
    public Member getMemberByEmail(String email) throws SQLException{
        String sql = "SELECT * FROM member WHERE email = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Member m = new Member();
                m.setMember_id(rs.getInt("member_id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));
                return m;
            }
            else{
                return null;
            }
        }
    }

    /* 회원 정보 수정 */
    public boolean updateMember(Member m) throws SQLException{
        String sql = "UPDATE member SET name = ?, email = ?, password = ?,updated_at = CURRENT_TIMESTAMP WHERE member_id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, m.getName());
            pstmt.setString(2,m.getEmail());
            pstmt.setString(3, m.getPassword());
            pstmt.setInt(4,m.getMember_id());

            int result = pstmt.executeUpdate();
            return result > 0;
        }
    }

    /* 이메일 기준으로 회원정보 삭제 */
    public boolean deleteMember(String email) throws SQLException{
        String sql = "DELETE TABLE FROM member where email = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);

            int result = pstmt.executeUpdate();
            return result > 0;
        }
    }
}