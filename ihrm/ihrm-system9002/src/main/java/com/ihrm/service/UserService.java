package com.ihrm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.entity.system.User;
import com.ihrm.entity.system.UserRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

  Page<User> findByPage(Map<String, Object> map, int page, int size);

  User findById(long id);

  void updateById(User user);
  void save(User user);
  void deleteById(long id);


  void deleteUserRole(String userId, List<String> roleIds);

  void deleteUserRole(Long userId);

  void assignRoles(String userId, List<String> roleIds);

  Set<String> findRolesByUserId(Long userId);

  User findByMobile(String mobile);

}
