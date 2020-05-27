package com.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.dao.RoleDao;
import com.ihrm.dao.UserDao;
import com.ihrm.dao.UserRoleDao;
import com.ihrm.entity.system.Role;
import com.ihrm.entity.system.User;
import com.ihrm.entity.system.UserRole;
import com.ihrm.service.UserService;
import com.ihrm.utils.IdWorker;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;
  @Resource
  private UserRoleDao userRoleDao;

  @Override
  public Page<User> findByPage(Map<String, Object> map, int page, int size) {
    Page<User> userPage = new Page<>(page, size);
    System.out.println(map.get("companyId"));

    QueryWrapper<User> wrapper = new QueryWrapper<>();
    if (!StringUtils.isEmpty(map.get("companyId"))) wrapper.eq("company_id",map.get("companyId"));
    if (!StringUtils.isEmpty(map.get("departmentId"))) wrapper.eq("department_id",map.get("departmentId"));
    return userDao.selectPage(userPage, wrapper);
  }

  @Override
  public User findById(long id) {
    return userDao.selectById(id);
  }

  @Override
  public void updateById(User user) {
    User userInfo = userDao.selectById(user.getId());
    userInfo.setUsername(user.getUsername());
    userInfo.setPassword(user.getPassword());

    userInfo.setDepartmentId(user.getDepartmentId());
    userInfo.setDepartmentName(user.getDepartmentName());
    userDao.updateById(userInfo);
  }

  @Override
  public void save(User user) {
    if (user.getPassword() == null) user.setPassword("123456");
    user.setEnableState(1);
    user.setId(new IdWorker().nextId());
    userDao.insert(user);
  }

  @Override
  public void deleteById(long id) {
    userDao.deleteById(id);
  }

  @Override
  public void deleteUserRole(String userId, List<String> roleIds) {
    QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
    List<Long> list = roleIds.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

    wrapper.eq("user_id", Long.parseLong(userId)).in("role_id",list);
    List<UserRole> userRoles = userRoleDao.selectList(wrapper);

    if (userRoles != null) userRoles.forEach(e -> {
      QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
      userRoleQueryWrapper.eq("role_id", e.getRoleId()).and(c -> c.eq("user_id",userId));
      userRoleDao.delete(userRoleQueryWrapper);
    });
  }

  @Override
  public void deleteUserRole(Long userId) {
    QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId);
    userRoleDao.delete(wrapper);
  }

  @Override
  public void assignRoles(String userId, List<String> roleIds) {
    roleIds.stream().distinct().forEach(e -> userRoleDao.insert(new UserRole(Long.parseLong(userId), Long.parseLong(e))));
  }

  @Override
  public Set<String> findRolesByUserId(Long userId) {
    QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_id", userId);
    return userRoleDao.selectList(queryWrapper).stream()
        .map(UserRole::getRoleId)
        .map(String::valueOf)
        .collect(Collectors.toSet());
  }

  @Override
  public User findByMobile(String mobile){
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("mobile", mobile);
    return userDao.selectOne(wrapper);
  }


}
