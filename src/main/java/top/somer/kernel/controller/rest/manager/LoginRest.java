package top.somer.kernel.controller.rest.manager;

import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.somer.kernel.configure.oauth2.OAuth2Token;
import top.somer.kernel.controller.AbstractController;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.dto.AjaxResult;
import top.somer.kernel.model.dto.AjaxResultState;
import top.somer.kernel.model.dto.CndType;
import top.somer.kernel.model.dto.manager.LoginResult;
import top.somer.kernel.model.mapped.Person;
import top.somer.kernel.model.mapped.system.JWTToken;
import top.somer.kernel.model.mapped.system.SysUser;
import top.somer.kernel.model.mapped.system.SysUserToken;
import top.somer.kernel.model.param.JWTParam;
import top.somer.kernel.utils.FunctionUtils;
import top.somer.kernel.utils.JwtUtils;
import top.somer.kernel.utils.RuntimeUtils;

import java.util.Date;
import java.util.Map;

/**
 * 登录
 *
 * @author Somer
 * @date 2018-03-02 16:20
 **/
@RestController
@RequestMapping("/api")
public class LoginRest extends AbstractController {

    @Autowired
    private IBaseOperate baseOperate;

    @Autowired
    private JWTParam jwtParam;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password) {
        SysUser user = (SysUser) baseOperate.getOneByColumn(SysUser.class, Cnd.where("username", "=", username));
        if (null != user) {// 用户存在，执行登录
            if (user.getStatus() == 0) {
                return new AjaxResult(AjaxResultState.CONTENT_ERROR, "账号已被锁定，请联系管理员!");
            }
            if (!user.getPassword().equals(password)) {
                return new AjaxResult(AjaxResultState.CONTENT_ERROR, "密码错误!");
            } else {
                return doLogin(username, user.getId());
            }
        } else {// 不存在，到Person查询是否存在，不存在，直接返回，存在，自动添加到SysUser表
            Object personObject = baseOperate.getOneByColumn(Person.class, Cnd.where("username", "=", username));
            if (null == personObject) {
                return new AjaxResult(AjaxResultState.CONTENT_ERROR, "用户不存在!");
            } else {// 自动添加到SysUser表，默认密码123456
                Person person = (Person) personObject;
                SysUser sysUser = new SysUser();
                sysUser.setPersonId(person.getId());
                sysUser.setRoleId(person.getType());
                sysUser.setCreateTime(RuntimeUtils.getCurrentTimestamp());
                sysUser.setStatus(1);
                sysUser.setUsername(person.getUsername());
                sysUser.setPassword("123456");//默认密码
                if (baseOperate.add(sysUser).getCode().equals(AjaxResultState.INNER_ERROR)) {
                    return new AjaxResult(AjaxResultState.CONTENT_ERROR, "系统异常，请联系管理员!");
                } else {
                    return doLogin(username, sysUser.getId());
                }
            }
        }
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
        Date expireTime = new Date(jwtParam.getExpire() + now.getTime());
        SysUserToken userToken = (SysUserToken) baseOperate.getOneByColumn(SysUserToken.class, Cnd.where("user_id", "=", userId));
        // 判断是否生成过token
        if (null == userToken) {
            userToken = new SysUserToken();
            userToken.setExpireTime(expireTime);// 设置token过期时间
            userToken.setToken(newToken);// 设置token
            userToken.setUpdateTime(now);
            userToken.setUserId(userId);
            // 保存token到数据库
            baseOperate.add(userToken);
        } else {
            userToken.setExpireTime(expireTime);// 设置token过期时间
            userToken.setToken(newToken);// 设置token
            userToken.setUpdateTime(now);
            // 更新token
            baseOperate.update(userToken);
        }
        SecurityUtils.getSubject().login(new OAuth2Token(token.getToken()));
        Map<String, CndType> map = Maps.newHashMap();
        map.put("su.id", FunctionUtils.Cond("=", userToken.getUserId()));
        LoginResult result = (LoginResult) baseOperate.get(LoginResult.class, "currentUser.get", map).getData();
        return new AjaxResult(AjaxResultState.OK, "登录成功！", new LoginResult(newToken, result.getUsername(), result.getRoleName()));
    }

    /**
     * 刷新token
     *
     * @return
     */
    @PostMapping("/token/fresh")
    public AjaxResult freshToken() {
        SysUser sysUser = getUser();
        Date now = new Date();
        // 过期时间
        Date expire = new Date(jwtParam.getExpire() + now.getTime());
        JWTToken jwtToken = JwtUtils.createJWT(sysUser.getUsername(), sysUser.getId().toString(), new Date(jwtParam.getExpire()), jwtParam.getBase64Secret());
        SysUserToken userToken = (SysUserToken) baseOperate.getOneByColumn(SysUserToken.class, Cnd.where("user_id", "=", sysUser.getId()));
        userToken.setToken(jwtToken.getToken());
        userToken.setUpdateTime(now);
        userToken.setExpireTime(expire);
        if (baseOperate.update(userToken).getCode().equals(200)) {
            return new AjaxResult(AjaxResultState.OK, "刷新成功", jwtToken.getToken());
        } else {
            return new AjaxResult(AjaxResultState.INNER_ERROR, AjaxResultState.INNER_ERROR_MESSAGE);
        }
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PutMapping("/updatePassword")
    public AjaxResult updatePassword(String oldPassword, String newPassword) {
        SysUser sysUser = (SysUser) baseOperate.get(SysUser.class, getUserId()).getData();
        if (!oldPassword.equals(sysUser.getPassword())) {//原始密码不正确
            return new AjaxResult(AjaxResultState.CONTENT_ERROR, "原始密码不正确!");
        } else if (oldPassword.equals(newPassword)) {//新旧密码相同
            return new AjaxResult(AjaxResultState.CONTENT_ERROR, "新密码不能与旧密码相同!");
        } else {
            sysUser.setPassword(newPassword);
            if (baseOperate.update(sysUser).getCode().equals(AjaxResultState.OK)) {
                return new AjaxResult(AjaxResultState.OK, "修改成功!");
            } else {
                return new AjaxResult(AjaxResultState.INNER_ERROR, "系统异常，请联系管理员!");
            }
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @GetMapping("/currentUser")
    public AjaxResult getCurrentUser() {
        return baseOperate.get(SysUser.class, getUserId());
    }
}
