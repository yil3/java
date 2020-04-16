package com.universe.dao;

import com.universe.entity.UserFile;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileDAO {
    List<UserFile> findByUid(Integer uid);

    int save (UserFile userFile);

    UserFile findById(Integer id);

    void update(UserFile userFile);

    void deleteById(Integer id);
}
