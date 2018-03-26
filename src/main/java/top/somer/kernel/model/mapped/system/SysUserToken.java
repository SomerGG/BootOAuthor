package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token
 *
 * @author Somer
 * @date 2018-03-02 11:23
 **/
@Table("sys_user_token")
public class SysUserToken extends IntegerPrototype implements Serializable {

    @Column("user_id")
    @Comment("用户ID")
    private Integer userId;

    @Column(value = "token")
    @Comment("用户token")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String token;

    @Column("expire_time")
    @Comment("过期时间")
    private Date expireTime;

    @Column("update_time")
    @Comment("更新时间")
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
