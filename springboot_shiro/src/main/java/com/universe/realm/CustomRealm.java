package com.universe.realm;


import com.universe.entity.User;
import com.universe.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {

    public void setName(String name){
        super.setName("CustomRealm");
    }

    @Resource
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户数据
        User user  = (User) principals.getPrimaryPrincipal();

        // 添加角色信息，可以在数据库中获取
        // Set<String> roles = new HashSet<>();
        // Set<String> permissions = new HashSet<>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user.getUsername().equals("root")){
            info.addRole("admin");
        }
        info.addStringPermission("vip8");
        

        // info.addRoles(roles);  //存储角色
        // info.addStringPermissions(permissions);  //存储权限

        return info;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取当前用户
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String username = upt.getUsername();
        String password = new String(upt.getPassword());


        // 查询数据库
        User user = userService.findByUserName(username);

        if(user != null && user.getPassword().equals(password)) {
            // 第一个参数传递当前用户对象到授权方法内
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;

    }


}