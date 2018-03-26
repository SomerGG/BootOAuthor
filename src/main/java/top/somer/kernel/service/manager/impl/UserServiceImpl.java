package top.somer.kernel.service.manager.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.somer.kernel.dao.base.impl.BaseOperate;
import top.somer.kernel.dao.manager.IUserDao;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.dto.manager.Perms;
import top.somer.kernel.model.mapped.system.*;
import top.somer.kernel.service.manager.IUserService;
import top.somer.kernel.utils.AjaxResultUtils;
import top.somer.kernel.utils.RuntimeUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Somer
 * @date 2018-03-05 20:35
 **/
@Service
public class UserServiceImpl extends BaseOperate implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public Set<String> getPermissions(Integer userId) {
        List<String> permsList = Lists.newArrayList();
        List<Perms> list = userDao.getPermissions(userId);
        if (null != list && list.size() > 0) {
            for (Perms temp : list) {
                permsList.add(temp.getPerms());
            }
            // 用户权限列表
            Set<String> permsSet = new HashSet<>();
            for (String perms : permsList) {
                if (StringUtils.isBlank(perms)) {
                    continue;
                }
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
            return permsSet;
        } else {
            return null;
        }
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public AjaxResult addUser(SysUser sysUser) {
        sysUser.setCreateTime(RuntimeUtils.getCurrentTimestamp());
        sysUser.setPassword("qweasd1234");// 默认密码
        if (null == sysUser.getRoleId() || "".equals(sysUser.getRoleId())) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先选择用户角色！");
        } else {
            if (dao.query(SysUser.class, Cnd.where("username", "=", sysUser.getUsername())).size() > 0) {
                return new AjaxResult(AjaxResultState.CONTENTERROR, "用户名已存在！");
            } else {
                if (null != dao.insert(sysUser)) {
                    return new AjaxResult(AjaxResultState.OK, "添加用户成功！");
                } else {
                    return new AjaxResult(AjaxResultState.INNERERROR, "内部错误！");
                }
            }
        }
    }

    @Override
    public AjaxResult delUser(String id) {
        int state = dao.clear(SysUser.class, Cnd.where("id", "in", id));
        if (state > 0) {
            dao.clear(SysUserToken.class, Cnd.where("user_id", "in", id));
        }
        return AjaxResultUtils.delInfoMessage(state > 0 ? true : false);
    }

    @Override
    public AjaxResult getUser(Integer userId) {
        return AjaxResultUtils.getInfoMessage(dao.fetch(SysUser.class, Cnd.where("id", "=", userId)));
    }

    @Override
    public AjaxResult addRole(SysRole sysRole) {
        if (dao.query(SysRole.class, Cnd.where("role_name", "=", sysRole.getRoleName())).size() > 0) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "已经存在" + sysRole.getRoleName() + "角色了！");
        } else {
            if (null != dao.insert(sysRole)) {
                return new AjaxResult(AjaxResultState.OK, "添加角色成功！");
            } else {
                return new AjaxResult(AjaxResultState.INNERERROR, "内部错误！");
            }
        }
    }

    @Override
    public AjaxResult setRoleMenu(String menuIds, Integer roleId) {
        SysRole sysRole = dao.fetch(SysRole.class, Cnd.where("id", "=", roleId));
        if (null == sysRole) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先选择角色！");
        }
        List<SysMenu> menuList = dao.query(SysMenu.class, Cnd.where("id", "in", menuIds));
        if (null == menuList || menuList.size() <= 0) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先选择菜单！");
        } else {
            // 清除原有菜单
            dao.clearLinks(sysRole, "menuList");
            // 重新给角色设置新菜单
            sysRole.setMenuList(menuList);
            dao.insertRelation(sysRole, "menuList");
            return new AjaxResult(AjaxResultState.OK, "设置角色菜单成功！");
        }
    }

    @Override
    public AjaxResult getRoleMenu(Integer roleId) {
        SysRole sysRole = dao.fetch(SysRole.class, Cnd.where("id", "=", roleId));
        if (null == sysRole) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "未获取到相关角色!");
        } else {
            sysRole = dao.fetchLinks(sysRole, "menuList");
            List<SysMenu> menuList = sysRole.getMenuList();
            if (null == menuList || menuList.size() <= 0) {
                return new AjaxResult(AjaxResultState.CONTENTERROR, "该角色尚未分配菜单!");
            } else {
                return new AjaxResult(AjaxResultState.OK, "获取菜单成功!", menuList);
            }
        }
    }

    @Override
    public AjaxResult setRolePermission(String permsId, Integer roleId) {
        SysRole sysRole = dao.fetch(SysRole.class, Cnd.where("id", "=", roleId));
        if (null == sysRole) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先选择角色！");
        }
        List<SysPermission> permissionList = dao.query(SysPermission.class, Cnd.where("id", "in", permsId));
        if (null == permissionList || permissionList.size() <= 0) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先选择权限！");
        } else {
            // 清除原有权限
            dao.clearLinks(sysRole, "permissionList");
            // 重新给角色设置新菜单
            sysRole.setPermissionList(permissionList);
            dao.insertRelation(sysRole, "permissionList");
            return new AjaxResult(AjaxResultState.OK, "设置角色权限成功！");
        }
    }
}
