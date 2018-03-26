package top.somer.kernel.controller.rest.manager;

import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.controller.action.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.dao.manager.IMenuDao;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.mapped.system.SysMenu;
import top.somer.kernel.service.manager.IMenuService;
import top.somer.kernel.utils.FunctionUtils;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author Somer
 * @date 2018-03-05 20:37
 **/
@RestController
@RequestMapping("/api")
public class MenuRest extends AbstractController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private IBaseOperate baseOperate;

    /**
     * 导航菜单
     *
     * @return
     */
    @GetMapping("/menu/main")
    public AjaxResult maniMenu() {
        return menuService.getUserMenu(getUserId());
    }

    /**
     * 获取菜单列表
     *
     * @param pager
     * @param type
     * @return
     */
    @GetMapping("/menu")
    @RequiresPermissions("sys:menu:list")
    public AjaxResult getMenu(Pager pager, String type) {
        Map<String, CndType> params = Maps.newHashMap();
        if (FunctionUtils.checkParam(type)) {
            params.put("type", FunctionUtils.Cond("=", type));
        }
        return baseOperate.getPager(params, null, SysMenu.class, pager);
    }

    /**
     * 获取所有一级菜单
     *
     * @return
     */
    @GetMapping("/menu/parent")
    @RequiresPermissions("sys:menu:list")
    public AjaxResult getParentMenu() {
        Map<String, CndType> params = Maps.newHashMap();
        params.put("type", FunctionUtils.Cond("=", 0));
        return baseOperate.getNoPager(params, null, SysMenu.class);
    }

    /**
     * 查看菜单详情
     *
     * @param menuId
     * @return
     */
    @GetMapping("/menu/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public AjaxResult getMenu(@PathVariable Integer menuId) {
        return baseOperate.get(SysMenu.class, menuId);
    }

    /**
     * 添加菜单
     *
     * @param sysMenu
     * @return
     */
    @PostMapping("/menu")
    @RequiresPermissions("sys:menu:add")
    public AjaxResult addMenu(SysMenu sysMenu) {
        return baseOperate.add(sysMenu);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @PutMapping("/menu")
    @RequiresPermissions("sys:menu:update")
    public AjaxResult updateMenu(SysMenu sysMenu) {
        return baseOperate.update(sysMenu, sysMenu.getId());
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("/menu")
    @RequiresPermissions("sys:menu:delete")
    public AjaxResult delMenu(String id) {
        List<SysMenu> childMenuList = menuDao.getChildMenu(Integer.valueOf(id));
        if (null != childMenuList && childMenuList.size() > 0) {
            return new AjaxResult(AjaxResultState.CONTENTERROR, "请先删除子菜单或按钮！");
        } else {
            return baseOperate.delete(SysMenu.class, id);
        }
    }
}
