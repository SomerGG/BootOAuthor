package top.somer.kernel.configure.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author Somer
 * @date 2018-03-02 15:01
 **/
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
