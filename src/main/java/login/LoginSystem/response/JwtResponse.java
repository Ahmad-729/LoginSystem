package login.LoginSystem.response;

import java.util.List;

public class JwtResponse {

private String token;
private String type="Bearer";

private Long id;
private String username;
private String Email;
private List<String> roles;

    public JwtResponse(String accessToken, String type, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.type = type;
        this.id = id;
        this.username = username;
        Email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
