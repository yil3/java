package com.universe.config;

import java.util.LinkedHashMap;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

  // 设置安全管理器
  @Bean
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(defaultWebSecurityManager);
    /**
      * rele: 拥有某个角色权限才能访问
      * perms: 拥有某个资源的权限才能访问
      * anon: 无需认证
      * authc: 必须认证才能进入
      */
    LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
    
    // 添加权限设置
    filterMap.put("/add", "perms[vip8]");
    filterMap.put("/update", "authc");

    bean.setFilterChainDefinitionMap(filterMap);

    // 设置登录地址
    bean.setLoginUrl("/login");
    // 设置无权限访问跳转地址
    bean.setUnauthorizedUrl("/unauthorized");

    return bean;
  }

  // 关联Realm对象
  @Bean
  public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") final UserRealm userRealm){
    final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    return securityManager;
  }

  // 创建realm对象
  @Bean
  public UserRealm userRealm(){
    return new UserRealm();
  }

  // 创建前端交互Shiro拓展
  @Bean
  public ShiroDialect getShiroDialect(){
    return new ShiroDialect();
  }
}