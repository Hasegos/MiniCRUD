package dto.login;

public class Membership {

    private int id; // DB Serial
    private String name; // 이름
    private String email; // 이메일
    private String password; // 비밀번호

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }
}