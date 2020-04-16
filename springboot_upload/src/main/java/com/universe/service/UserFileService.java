package com.universe.service;

import com.universe.entity.UserFile;


import java.util.List;

public interface UserFileService {
    List<UserFile> findByUid(Integer uid);

    int save (UserFile userFile);

    UserFile findById(Integer id);

    void update(UserFile userFile);

    void deleteById(Integer id);
}
