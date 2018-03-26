package top.somer.kernel.model.param;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * APP信息
 *
 * @author Somer
 * @date 2018-03-16 18:20
 **/
@Component
@ConfigurationProperties(prefix ="jwt")
public class JWTParam {

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 加密密文
     */
    private String base64Secret;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }
}
