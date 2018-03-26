package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Somer
 * @date 2018-03-02 10:50
 **/
@Table("sys_user")
public class SysUser extends IntegerPrototype implements Serializable {

    /**
     * 角色ID
     */
    @Column("role_id")
    private Integer roleId;

    /**
     * 用户名
     */
    @Column("username")
    private String username;

    /**
     * 密码
     */
    @Column("password")
    private String password;

    /**
     * 状态  0：禁用   1：正常
     */
    @Column("status")
    @Comment(" 0：禁用  1：正常")
    private Integer status;

    /**
     * 人员ID
     */
    @Column("person_id")
    @Comment("人员ID")
    private Integer personId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private Date createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
