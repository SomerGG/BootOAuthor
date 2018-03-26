package top.somer.kernel.controller.action.manager;

import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.mapped.Person;
import top.somer.kernel.model.mapped.system.JWTToken;
import top.somer.kernel.model.mapped.system.SysUser;
import top.somer.kernel.model.mapped.system.SysUserToken;
import top.somer.kernel.model.param.JWTParam;
import top.somer.kernel.utils.JwtUtils;
import top.somer.kernel.utils.RuntimeUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Somer
 * @date 2018-03-05 10:37
 **/
@Controller
public class LoginController {

    @Autowired
    private IBaseOperate baseOperate;

    @Autowired
    private JWTParam jwtParam;


    @RequestMapping(value = {"/", "index", "main"})
    public String index() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (null == user) {
            return "redirect:/login";
        } else if (SecurityUtils.getSubject().getSession() == null) {
            return "redirect:/login";
        }
        return "index.html";
    }

    @GetMapping(value = "/login")
    public String login_page() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        return "login.html";
    }

    @GetMapping("/oauth/login")
    public String oauth2_login(String client_id, String callback, RedirectAttributes model) {
        if (client_id == null || callback == null) {
            return "redirect:/login";
        }
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:http://" + callback + "?token=123";
        }
        model.addAttribute("callback", callback);
        model.addAttribute("client_id", client_id);
        return "redirect:/login";
    }

    @GetMapping("/doLogin")
    public String oauthDoLogin(String username, String password, String clientId, String callback, HttpServletResponse response) {
        Map<String, Object> map = Maps.newHashMap();
        SysUser user = (SysUser) baseOperate.getOneByColumn(SysUser.class, Cnd.where("username", "=", username));
        if (null != user) {// 用户存在，执行登录
            if (user.getStatus() == 0) {
                map.put("message", "账号被锁定");
            }
            if (!user.getPassword().equals(password)) {
                map.put("message", "密码错误");
            } else {
                return returnUrl(username, user.getId(), clientId, callback, response);
            }
            return "redirect:/login";
        } else {// 不存在，到Person查询是否存在，不存在，直接返回，存在，自动添加到SysUser表
            Object personObject = baseOperate.getOneByColumn(Person.class, Cnd.where("username", "=", username));
            if (null == personObject) {
                map.put("message", "用户不存在");
            } else {// 自动添加到SysUser表，默认密码123456
                Person person = (Person) personObject;
                SysUser sysUser = new SysUser();
                sysUser.setPersonId(person.getId());
                sysUser.setRoleId(person.getType());
                sysUser.setCreateTime(RuntimeUtils.getCurrentTimestamp());
                sysUser.setStatus(1);
                sysUser.setUsername(person.getUsername());
                sysUser.setPassword("123456");//默认密码
                if (baseOperate.add(sysUser).getCode().equals(AjaxResultState.INNERERROR)) {
                    map.put("message", "系统异常");
                } else {
                    return returnUrl(username, sysUser.getId(), clientId, callback, response);
                }
            }
        }
        return "redirect:/login";
    }

    /**
     * 设置cookie，URL回跳
     *
     * @param username
     * @param userId
     * @param clientId
     * @param callback
     * @param response
     * @return
     */
    private String returnUrl(String username, Integer userId, String clientId, String callback, HttpServletResponse response) {
        AjaxResult ajaxResult = doLogin(username, userId);
        Cookie cookie = new Cookie("token", ((JWTToken) ajaxResult.getData()).getToken());
        response.addCookie(cookie);
        if (clientId != null || callback != null) {
            return "redirect:http://" + callback + "?token=123";
        }
        return "redirect:/";
    }

    /**
     * 登录生成JWTToken
     *
     * @param username
     * @param userId
     * @return
     */
    public AjaxResult doLogin(String username, Integer userId) {
        // JWTToken
        JWTToken token = JwtUtils.createJWT(username, userId.toString(), new Date(jwtParam.getExpire()), jwtParam.getBase64Secret());
        String newToken = token.getToken();
        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireTime = new Date(now.getTime() + jwtParam.getExpire());
        SysUserToken userToken = (SysUserToken) baseOperate.getOneByColumn(SysUserToken.class, Cnd.where("user_id", "=", userId));
        // 判断是否生成过token
        if (null == userToken) {
            userToken = new SysUserToken();
            userToken.setToken(newToken);// 设置token
            userToken.setExpireTime(expireTime);// 设置token过期时间
            userToken.setUpdateTime(now);
            userToken.setUserId(userId);
            // 保存token到数据库
            baseOperate.add(userToken);
        } else {
            userToken.setToken(newToken);// 设置token
            userToken.setExpireTime(expireTime);// 设置token过期时间
            userToken.setUpdateTime(now);
            // 更新token
            baseOperate.update(userToken, userToken.getId());
        }
        return new AjaxResult(AjaxResultState.OK, "登录成功！", token);
    }
}
