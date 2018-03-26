package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * API访问权限
 *
 * @author Somer
 * @date 2018-03-06 15:25
 **/
@Table("sys_permission")
public class SysPermission extends IntegerPrototype implements Serializable {

    /**
     * 名称
     */
    @Column("name")
    private String name;

    /**
     * 权限  (例如：menu:add, user:add)
     */
    @Column("perms")
    private String perms;

    /**
     * 描述
     */
    @Column("description")
    @ColDefine(type = ColType.TEXT)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
