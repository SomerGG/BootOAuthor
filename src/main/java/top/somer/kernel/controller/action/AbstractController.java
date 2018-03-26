package top.somer.kernel.controller.action;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.somer.kernel.model.mapped.system.SysUser;

/**
 * Controller公共组件
 *
 * @author Somer
 * @date 2018-03-05 14:14
 **/
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId() {
        return getUser().getId();
    }
}
