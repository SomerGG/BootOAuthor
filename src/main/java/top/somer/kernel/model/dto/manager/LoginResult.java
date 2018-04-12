package top.somer.kernel.model.dto.manager;

import java.io.Serializable;

/**
 * @author Somer
 * @date 2018-04-12 23:06
 **/
public class LoginResult implements Serializable {

    private String token;

    private String username;

    private String roleName;

    public LoginResult(String token, String username, String roleName) {
        this.token = token;
        this.username = username;
        this.roleName = roleName;
    }

    public LoginResult() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
