package top.somer.kernel.controller.rest.manager;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.mapped.system.SysUser;
import top.somer.kernel.service.manager.IUserService;
import top.somer.kernel.utils.FunctionUtils;

import java.util.Map;

/**
 * 用户管理
 *
 * @author Somer
 * @date 2018-03-07 20:30
 **/
@RequestMapping("/api")
@RestController
public class UserRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    @Autowired
    private IUserService userService;

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/user")
    @RequiresPermissions("sys:user:add")
    public AjaxResult addUser(SysUser sysUser) {
        return userService.addUser(sysUser);
    }

    /**
     * 更新用户
     *
     * @param sysUser
     * @return
     */
    @PutMapping("/user")
    @RequiresPermissions("sys:user:update")
    public AjaxResult updateUser(SysUser sysUser) {
        return baseOperate.update(sysUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/user")
    @RequiresPermissions("sys:user:delete")
    public AjaxResult delUser(String id) {
        return userService.delUser(id);
    }

    /**
     * 根据ID获取用户信息
     *
     * @return
     */
    @GetMapping("/user/{id}")
    @RequiresPermissions("sys:user:info")
    public AjaxResult getUserInfo(@PathVariable("id") Integer id) {
        return baseOperate.get(SysUser.class, id);
    }

    /**
     * 获取全部用户信息
     *
     * @param pager
     * @param username
     * @return
     */
    @GetMapping("/user")
    @RequiresPermissions("sys:user:list")
    public AjaxResult getAllUser(Pager pager, String username) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(username)) {
            params.put("username", FunctionUtils.Cond("like", username));
        }
        return baseOperate.getPager(params, null, SysUser.class, pager);
    }
}
