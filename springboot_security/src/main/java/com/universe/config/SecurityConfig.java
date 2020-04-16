package com.universe.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



  // 授权
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/lv01/**").hasRole("vip1")
        .antMatchers("/lv02/**").hasRole("vip2")
        .antMatchers("/lv03/**").hasRole("vip3")
        ;
    // 配置login页面
    http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password");
    // 登录成功跳转页面设置
    http.logout().logoutSuccessUrl("/");
    // 关闭跨域
    http.csrf().disable();
    // 记住我
    http.rememberMe().rememberMeParameter("remember");
  }

  // 认证
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
    .withUser("yil3").password(new BCryptPasswordEncoder().encode("123")).roles("vip2","vip3")
    .and()
    .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("vip1","vip2","vip3")
    ;
    
  }


}