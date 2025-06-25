package dto.library;

import java.sql.Timestamp;

public class Member {

    private int member_id; // 회원번호
    private String name; // 이름
    private String email; // 아이디
    private String password; // 비밀번호
    private Timestamp createdAt; // 최초 등록일
    private Timestamp updateAt; // 수정일

    // 기본 생성자
    public Member(){}


    public Member(int member_id, String name, String email, String password,
                  Timestamp createdAt, Timestamp updateAt) {
        this.member_id = member_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public void setMember_id(int member_id) { this.member_id = member_id; }
    public int getMember_id() { return member_id; }

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }

    public void setEmail(String id) { this.email = id; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setUpdateAt(Timestamp updateAt) { this.updateAt = updateAt; }
    public Timestamp getUpdateAt() { return updateAt; }
}