package top.somer.kernel.model.mapped.system;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author Somer
 * @date 2018-03-02 11:11
 **/
@Table("sys_role")
public class SysRole extends IntegerPrototype implements Serializable {

    /**
     * 角色类别(0:管理员，1:学校，2:机构)
     */
    @Column("type")
    private Integer type;

    /**
     * 角色名称
     */
    @Column("role_name")
    private String roleName;

    /**
     * 菜单信息
     */
    @ManyMany(relation = "sys_role_menu", from = "role_id", to = "menu_id")
    private List<SysMenu> menuList;

    /**
     * 权限信息
     */
    @ManyMany(relation = "sys_role_permission", from = "role_id", to = "perm_id")
    private List<SysPermission> permissionList;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}
