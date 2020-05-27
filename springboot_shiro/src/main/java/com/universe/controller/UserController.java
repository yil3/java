package com.universe.controller;

import com.universe.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
public class UserController {
    @RequestMapping("/login")
    public String login(String username, String password) {
        try {
            /*
             * 密码加密：
             * shiro提供md5加密
             * 参数1：加密内容
             * 参数2:盐
             * 参数3:散列次数
             * */
            //
            String pwd = new Md5Hash(password).toString();
            System.out.println(pwd);
            // 构造登录令牌
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, pwd);
            Subject subject = SecurityUtils.getSubject();
            // 请求登录
            subject.login(usernamePasswordToken);
            // 获取sessionId
            Serializable sessionId = subject.getSession().getId();
            // 获取存储的安全数据
            PrincipalCollection principals = subject.getPrincipals();
            User primaryPrincipal = (User) principals.getPrimaryPrincipal();
            System.out.println(primaryPrincipal);
            return "登录成功" + sessionId;
        } catch (Exception e) {
            return "用户名或密码错误";
        }
    }

    @RequestMapping("/save")
    public String save() {
        return "添加成功";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("pid","12345611112222");
        return "查询成功" + session.getAttribute("pid");
    }

    @RequestMapping("/delete")
    public String delete() {
        return "删除成功";
    }

    @RequestMapping("/update")
    public String update() {
        return "修改成功";
    }


    // 认证失败无权限跳转地址
    @RequestMapping("/unauthorized")
    public String unauthorized(int code) {
        return code == 1 ? "未登录" : "未授权";
    }
}
