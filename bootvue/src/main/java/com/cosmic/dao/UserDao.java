package com.cosmic.dao;

import com.cosmic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    List<User> findAll();
    int save(User user);
    int deleteById(int id);

    List<User> findByName(String name);
    User findById(int id);

    int update(User user);

}
