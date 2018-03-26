package top.somer.kernel.dao.manager.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import top.somer.kernel.dao.base.impl.BaseOperate;
import top.somer.kernel.dao.manager.IMenuDao;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.mapped.system.SysMenu;
import top.somer.kernel.utils.FunctionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Somer
 * @date 2018-03-05 20:15
 **/
@Service
public class MenuDaoImpl extends BaseOperate implements IMenuDao {

    @Override
    public List<Integer> getAllMenuId(Integer userId) {
        List<Integer> menuIdList = Lists.newArrayList();
        Map<String, CndType> params = Maps.newHashMap();
        params.put("su.id", FunctionUtils.Cond("=", userId));
        List<SysMenu> menuList = (List<SysMenu>) getNoPager(params, "allUserMenuId.get", SysMenu.class).getData();
        if (null != menuList && menuList.size() > 0) {
            for (SysMenu temp : menuList) {
                menuIdList.add(temp.getId());
            }
            return menuIdList;
        } else {
            return null;
        }
    }

    @Override
    public List<SysMenu> getChildMenu(Integer parentId) {
        return dao.query(SysMenu.class, Cnd.where("parent_id", "=", parentId).orderBy("order_num", "asc"));
    }
}
