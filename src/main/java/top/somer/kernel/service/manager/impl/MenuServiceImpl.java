package top.somer.kernel.service.manager.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.somer.kernel.dao.manager.IMenuDao;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.mapped.system.SysMenu;
import top.somer.kernel.service.manager.IMenuService;
import top.somer.kernel.utils.Constant;
import top.somer.kernel.utils.Constant.MenuType;

import java.util.List;

/**
 * @author Somer
 * @date 2018-03-05 20:04
 **/
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao menuDao;

    @Override
    public AjaxResult getUserMenu(Integer userId) {
        AjaxResult ajaxResult = new AjaxResult();
        List<SysMenu> menuList = null;
        if (userId == Constant.SUPER_ADMIN) {// 管理员
            menuList = getAllMenuList(null);
        } else {
            List<Integer> menuIdList = menuDao.getAllMenuId(userId);
            if (null != menuIdList && menuIdList.size() > 0) {
                menuList = getAllMenuList(menuIdList);
            }
        }
        if (null == menuList) {
            ajaxResult.setCode(AjaxResultState.CONTENT_ERROR);
            ajaxResult.setMessage("未获取到相关菜单信息！");
        } else {
            ajaxResult.setCode(AjaxResultState.OK);
            ajaxResult.setMessage("获取成功！");
            ajaxResult.setData(menuList);
        }
        return ajaxResult;
    }

    private List<SysMenu> getUserMenu(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList = menuDao.getChildMenu(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        List<SysMenu> userMenuList = Lists.newArrayList();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 获取所有菜单
     *
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Integer> menuIdList) {
        // 获取根菜单列表
        List<SysMenu> menuList = getUserMenu(0, menuIdList);
        // 递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList) {
        List<SysMenu> subMenuList = Lists.newArrayList();
        if (null != menuList && menuList.size() > 0) {
            for (SysMenu entity : menuList) {
                if (entity.getType() == MenuType.CATALOG.getValue()) {// 目录
                    entity.setList(getMenuTreeList(getUserMenu(entity.getId(), menuIdList), menuIdList));
                }
                subMenuList.add(entity);
            }
        }
        return subMenuList;
    }
}
