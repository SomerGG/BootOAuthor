package top.somer.kernel.dao.manager;


import top.somer.kernel.model.mapped.system.SysMenu;

import java.util.List;

/**
 * @author Somer
 * @date 2018-03-05 20:14
 **/
public interface IMenuDao {

    /**
     * 获取用户全部菜单ID
     *
     * @param userId
     * @return
     */
    List<Integer> getAllMenuId(Integer userId);

    /**
     * 根据父菜单ID，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenu> getChildMenu(Integer parentId);
}
