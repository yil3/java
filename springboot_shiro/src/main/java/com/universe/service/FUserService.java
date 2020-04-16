package com.universe.service;

import com.universe.entity.FUser;

public interface FUserService {

  FUser selectByName(String username);

}