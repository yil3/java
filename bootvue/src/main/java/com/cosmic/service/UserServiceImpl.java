package com.cosmic.service;

import com.cosmic.dao.UserDao;
import com.cosmic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
