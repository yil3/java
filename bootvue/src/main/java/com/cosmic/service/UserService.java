package com.cosmic.service;

import com.cosmic.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    int save(User user);
    int deleteById(int id);

    List<User> findByName(String name);
    User findById(int id);

    int update(User user);
}
