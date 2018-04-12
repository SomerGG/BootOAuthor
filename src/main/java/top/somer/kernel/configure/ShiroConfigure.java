package top.somer.kernel.configure;

import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.somer.kernel.configure.oauth2.OAuth2Filter;
import top.somer.kernel.configure.oauth2.OAuth2Realm;
import top.somer.kernel.configure.oauth2.Oauth2LogoutFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author Somer
 * @date 2018-03-02 13:00
 **/
@Configuration
public class ShiroConfigure {

    /**
     * Shiro拦截器
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login");//登录页面
        shiroFilter.setSuccessUrl("/index");//登录成功后的页面

        // OAuth2过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        filters.put("logout_cus", new Oauth2LogoutFilter());
        shiroFilter.setFilters(filters);

        // 配置拦截链
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/login", "anon");
        chains.put("/admin", "anon");
        chains.put("/logout", "logout_cus");
        chains.put("/druid/**", "anon");
        chains.put("/api/login", "anon");
        chains.put("/**", "oauth2");

        shiroFilter.setFilterChainDefinitionMap(chains);
        return shiroFilter;
    }

    /**
     * Session管理
     *
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    /**
     * 安全管理
     *
     * @param oAuth2Realm
     * @param sessionManager
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(oAuth2Realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
