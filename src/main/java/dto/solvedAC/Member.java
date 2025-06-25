package dto.solvedAC;

public class Member {

    private int id; // DB Serial
    private String name; // 이름
    int solvedCount; // 문제 푼수
    int Class; // 클래스 단계
    String joinedAt; // 가입일

    public Member(){}

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setSolvedCount(int solvedCount) { this.solvedCount = solvedCount; }
    public int getSolvedCount() { return solvedCount; }

    public void setClass(int Class) { this.Class = Class; }
    public int getclass() { return Class; }

    public void setJoinedAt(String joinedAt) { this.joinedAt = joinedAt; }
    public String getJoinedAt() { return joinedAt; }
}