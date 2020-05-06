package com.universe.config;


import com.universe.entity.FUser;
import com.universe.service.FUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;



public class UserRealm extends AuthorizingRealm {

  @Autowired
  private FUserService fUserService;

  // 授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // info.addStringPermission("vip8");

    // 获取当前用户
    Subject subject = SecurityUtils.getSubject();
    FUser fUser = (FUser) subject.getPrincipal();

    info.addStringPermission(fUser.getPerms());

    return info;
  }


  // 认证
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // 获取当前用户
//    String principal = (String) token.getPrincipal();
    UsernamePasswordToken userToken = (UsernamePasswordToken) token;
    // 查询数据库
    FUser fUser = fUserService.selectByName(userToken.getUsername());

    if(fUser == null)return null;
    // 第一个参数传递当前用户对象到授权方法内
	  return new SimpleAuthenticationInfo(fUser, fUser.getPassword(),this.getName());
  }
  


}