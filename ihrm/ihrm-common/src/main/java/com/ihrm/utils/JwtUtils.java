package com.ihrm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class JwtUtils {

  private static String key = "ihrm";  // 设置机密方式
  private static Long ttl = 3600000L;  //设置过期时间


  public static String create(Long id, String name, Map<String,Object> map){
    long now = System.currentTimeMillis();
    long exp = now + ttl;

    JwtBuilder jwtBuilder = Jwts.builder()
        .setId(String.valueOf(id))
        .setSubject(name)
        .setIssuedAt(new Date())   // 设置发布token时间
        .signWith(SignatureAlgorithm.HS256, key);  //加密

    map.forEach(jwtBuilder::claim);  // 根据map添加claim

    jwtBuilder.setExpiration(new Date(exp));  // 过期时间

    return jwtBuilder.compact();
  }

  public static Claims parse(String token){
    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
  }

}
