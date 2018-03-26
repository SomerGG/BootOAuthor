package top.somer.kernel.dao.manager;

import top.somer.kernel.model.dto.manager.Perms;
import top.somer.kernel.model.mapped.system.SysUser;

import java.util.List;

/**
 * @author Somer
 * @date 2018-03-05 20:29
 **/
public interface IUserDao {

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    List<Perms> getPermissions(Integer userId);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);
}
