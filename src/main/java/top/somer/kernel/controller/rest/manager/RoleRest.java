package top.somer.kernel.controller.rest.manager;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.dto.manager.Perms;
import top.somer.kernel.model.mapped.system.SysPermission;
import top.somer.kernel.model.mapped.system.SysRole;
import top.somer.kernel.service.manager.IUserService;
import top.somer.kernel.utils.Constant;
import top.somer.kernel.utils.FunctionUtils;

import java.util.Map;

/**
 * 角色管理
 *
 * @author Somer
 * @date 2018-03-06 11:35
 **/
@RestController
@RequestMapping("/api")
public class RoleRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    @Autowired
    private IUserService userService;

    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/role")
    @RequiresPermissions("sys:role:add")
    public AjaxResult addRole(SysRole sysRole) {
        return baseOperate.add(sysRole);
    }

    /**
     * 获取全部角色信息
     *
     * @param roleName
     * @param pager
     * @return
     */
    @GetMapping("/role")
    @RequiresPermissions("sys:roll:list")
    public AjaxResult getRole(String roleName, Pager pager) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(roleName)) {
            params.put("role_name", FunctionUtils.Cond("like", roleName));
        }
        return baseOperate.getPager(params, null, SysRole.class, pager);
    }

    /**
     * 设置角色菜单信息
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    @PostMapping("/setRoleMenu")
    @RequiresPermissions("sys:role:set")
    public AjaxResult setRoleMenu(String menuIds, Integer roleId) {
        return userService.setRoleMenu(menuIds, roleId);
    }

    /**
     * 设置角色权限信息
     *
     * @param perms
     * @param roleId
     * @return
     */
    @PostMapping("/setRolePermission")
    @RequiresPermissions("sys:perms:set")
    public AjaxResult setRolePermission(String perms, Integer roleId) {
        return userService.setRolePermission(perms, roleId);
    }

    /**
     * 获取角色菜单
     *
     * @return
     */
    @GetMapping("/roleMenu")
    @RequiresPermissions("sys:role:menu")
    public AjaxResult getRoleMenu(Integer roleId) {
        return userService.getRoleMenu(roleId);
    }

    /**
     * 添加权限
     *
     * @param sysPermission
     * @return
     */
    @PostMapping("/permission")
    @RequiresPermissions("sys:perms:add")
    public AjaxResult addPermission(SysPermission sysPermission) {
        return baseOperate.add(sysPermission);
    }

    /**
     * 获取权限信息
     *
     * @param pager
     * @param name
     * @return
     */
    @GetMapping("/permission")
    @RequiresPermissions("sys:perms:list")
    public AjaxResult getPermission(Pager pager, String name) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(name)) {
            params.put("name", FunctionUtils.Cond("like", name));
        }
        return baseOperate.getPager(params, null, SysPermission.class, pager);
    }

    /**
     * 获取角色权限信息
     *
     * @param roleId
     * @return
     */
    @GetMapping("/rolePerms")
    @RequiresPermissions("sys:role:perms")
    public AjaxResult getRolePermission(Integer roleId) {
        Map<String, CndType> params = Maps.newHashMap();
        if (roleId.equals(Constant.SUPER_ADMIN)) {
            return baseOperate.getNoPager(null, "permissions.all", Perms.class);
        } else {
            params.put("srp.role_id", FunctionUtils.Cond("=", roleId));
            return baseOperate.getNoPager(params, "rolePermission.all", Perms.class);
        }
    }
}
