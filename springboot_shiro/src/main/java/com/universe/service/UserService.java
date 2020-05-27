package com.universe.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.universe.dao.UserDao;
import com.universe.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User findByUserName(String username){
        return userDao.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
