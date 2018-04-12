package top.somer.kernel.controller.action.manager;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.somer.kernel.model.mapped.system.SysUser;

/**
 * 登录Controller
 *
 * @author Somer
 * @date 2018-03-05 10:37
 **/
@Controller
public class LoginController {

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index", "/main"})
    public String index() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (null == user) {
            return "redirect:/login";
        } else if (SecurityUtils.getSubject().getSession() == null) {
            return "redirect:/login";
        }
        return "index.html";
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        return "login.html";
    }
}
