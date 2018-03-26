package top.somer.kernel.service.manager;

import top.somer.kernel.model.dto.AjaxResult;

/**
 * @author Somer
 * @date 2018-03-05 20:03
 **/
public interface IMenuService {

    /**
     * 获取用户菜单
     *
     * @param userId
     * @return
     */
    AjaxResult getUserMenu(Integer userId);
}
