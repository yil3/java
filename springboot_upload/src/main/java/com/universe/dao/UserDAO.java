package com.universe.dao;

import com.universe.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    User login(User user);
}
