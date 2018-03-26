package top.somer.kernel.model.mapped.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created somuns by 2018/3/12
 * Description:
 */
public class JWTToken  implements Serializable {

    private static final long serialVersionUID = -7380137851135770553L;

    private String token;

    private Date updateTime;

    public JWTToken(String token, Date updateTime) {
        this.token = token;
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
