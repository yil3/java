package com.universe.config;

import com.universe.realm.CustomRealm;
import com.universe.session.CustomSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {

    // 设置过滤
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager);
        /**
         * rele: 拥有某个角色权限才能访问
         * perms: 拥有某个资源的权限才能访问
         * anon: 无需认证
         * authc: 必须认证(登录)才能进入
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        // 添加权限设置
        filterMap.put("/save", "authc");
        filterMap.put("/delete", "roles[admin]");
        filterMap.put("/update", "perms[vip8]");
        filterMap.put("/findAll", "anon");

        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录地址
        bean.setLoginUrl("/unauthorized?code=1");
        // 设置无权限访问跳转地址
        bean.setUnauthorizedUrl("/unauthorized?code=2");

        return bean;
    }


    @Bean
    public SecurityManager getSecurityManager(CustomRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Realm注册到安全管理器
        securityManager.setRealm(realm);
        // 将自定义session注册到安全管理器
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    // 创建自定义realm对象
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    // @Bean
    // public FormAuthenticationFilter formAuthenticationFilter (){
    //   FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
    //   formAuthenticationFilter.setUsernameParam("username");
    //   formAuthenticationFilter.setPasswordParam("password");
    //   formAuthenticationFilter.setRememberMeParam("rememberMe");
    //   return formAuthenticationFilter;
    // }

    // 开启对shior注解的支持
    // @Bean
    // public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
    //         SecurityManager securityManager) {
    //   AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    //   advisor.setSecurityManager(securityManager);
    //   return advisor;
    // }


    /*************添加Shiro会话管理配置*************/

    @Value("${spring.redis.host}")
    private String host;
    @Value(("${spring.redis.port}"))
    private int port;


    // redis控制器，redisDao操作redis
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        return redisManager;
    }

    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    // 会话管理器
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(redisSessionDAO());
        // 禁用Cookie
        // customSessionManager.setSessionIdCookieEnabled(false);
        // 禁用url重写 url;jsessionid=id
        customSessionManager.setSessionIdUrlRewritingEnabled(false);
        // 删除失效的session
        customSessionManager.setDeleteInvalidSessions(true);
        // session失效时间 单位毫秒 默认1800000L半小时
        customSessionManager.setGlobalSessionTimeout(60 * 60 * 2 * 1000);
        return customSessionManager;
    }

    // 缓存管理器
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 存入session的bean要有个唯一标识 参数：entity字段名，默认id
        // redisCacheManager.setPrincipalIdFieldName("username");
        return redisCacheManager;
    }

}
