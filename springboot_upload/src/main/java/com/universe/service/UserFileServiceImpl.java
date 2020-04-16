package com.universe.service;

import com.universe.dao.UserFileDAO;
import com.universe.entity.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileDAO userFileDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserFile> findByUid(Integer uid) {
        return userFileDAO.findByUid(uid);
    }

    @Override
    public int save(UserFile userFile) {
        String isImg = userFile.getType().startsWith("image") ? "是" : "否";
        userFile.setIsImg(isImg);
        userFile.setDowncounts(0);
        userFile.setUploadTime(new Date());
        return userFileDAO.save(userFile);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserFile findById(Integer id) {
        return userFileDAO.findById(id);
    }

    @Override
    public void update(UserFile userFile) {
        userFileDAO.update(userFile);
    }

    @Override
    public void deleteById(Integer id) {
        userFileDAO.deleteById(id);
    }


}
