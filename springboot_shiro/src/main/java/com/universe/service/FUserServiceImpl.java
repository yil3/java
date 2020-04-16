package com.universe.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.universe.dao.FUserDao;
import com.universe.entity.FUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FUserServiceImpl implements FUserService {

  @Autowired
  private FUserDao fUserDao;

  @Override
  public FUser selectByName(String username) {
    QueryWrapper<FUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username",username);
    return fUserDao.selectOne(queryWrapper);

  }


}