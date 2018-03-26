package top.somer.kernel.configure.oauth2;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Cnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.somer.kernel.dao.base.IBaseOperate;
import top.somer.kernel.model.mapped.system.SysUser;
import top.somer.kernel.model.mapped.system.SysUserToken;
import top.somer.kernel.model.param.JWTParam;
import top.somer.kernel.service.manager.IUserService;
import top.somer.kernel.utils.JwtUtils;

import java.util.Set;

/**
 * OAuth2认证
 *
 * @author Somer
 * @date 2018-03-02 13:50
 **/
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private JWTParam jwtParam;

    @Autowired
    private IBaseOperate baseOperate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(权限验证)
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        Integer userId = sysUser.getId();
        // 用户权限列表
        Set<String> permsSet = userService.getPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始登录验证......");
        String accessToken = (String) token.getPrincipal();
        // 根据accessToken，查询用户token信息
        SysUserToken userToken = (SysUserToken) baseOperate.getOneByColumn(SysUserToken.class, Cnd.where("token", "=", accessToken));
        Claims claims = JwtUtils.parseJWT(accessToken, jwtParam.getBase64Secret());
        // token失效
        if (claims == null || claims.getExpiration().getTime() < System.currentTimeMillis() || null == userToken) {
            throw new IncorrectCredentialsException("token失效，请重新登录!");
        }
        // 查询用户信息
        SysUser sysUser = userService.getUserByUsername((String) claims.get("username"));
        if (sysUser == null) {
            throw new UnknownAccountException("账户不存在，请重新输入!");
        }
        if (sysUser.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定，请联系管理员!");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, accessToken, getName());
        return info;
    }
}
