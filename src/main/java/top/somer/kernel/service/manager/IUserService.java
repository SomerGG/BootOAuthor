package top.somer.kernel.service.manager;

import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.mapped.system.SysRole;
import top.somer.kernel.model.mapped.system.SysUser;

import java.util.Set;

/**
 * @author Somer
 * @date 2018-03-05 20:34
 **/
public interface IUserService {

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    AjaxResult addUser(SysUser sysUser);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    AjaxResult delUser(String id);

    /**
     * 根据用户ID获取用户
     *
     * @param userId
     * @return
     */
    AjaxResult getUser(Integer userId);


    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    AjaxResult addRole(SysRole sysRole);

    /**
     * 给角色设置菜单
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    AjaxResult setRoleMenu(String menuIds, Integer roleId);

    /**
     * 获取角色的所有菜单
     *
     * @param roleId
     * @return
     */
    AjaxResult getRoleMenu(Integer roleId);

    /**
     * 给角色设置权限
     *
     * @param permsId
     * @param roleId
     * @return
     */
    AjaxResult setRolePermission(String permsId, Integer roleId);

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    Set<String> getPermissions(Integer userId);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);
}
