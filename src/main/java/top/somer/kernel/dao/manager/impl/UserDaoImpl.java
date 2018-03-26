package top.somer.kernel.dao.manager.impl;

import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import top.somer.kernel.dao.base.impl.BaseOperate;
import top.somer.kernel.dao.manager.IUserDao;
import top.somer.kernel.model.dto.manager.Perms;
import top.somer.kernel.model.mapped.system.SysUser;
import top.somer.kernel.utils.Constant;
import top.somer.kernel.utils.ResultUtils;

import java.util.List;

/**
 * @author Somer
 * @date 2018-03-05 20:30
 **/
@Service
public class UserDaoImpl extends BaseOperate implements IUserDao {

    @Override
    public List<Perms> getPermissions(Integer userId) {
        if (userId.equals(Constant.SUPER_ADMIN)) {//超级管理员，全部权限
            return ResultUtils.getSqlListNoPager(Perms.class, dao,
                    "permissions.all", null);
        } else {
            return ResultUtils.getSqlListNoPager(Perms.class, dao,
                    "userPermissions.all", Cnd.where("su.id", "=", userId));
        }
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return dao.fetch(SysUser.class, Cnd.where("username", "=", username));
    }
}
