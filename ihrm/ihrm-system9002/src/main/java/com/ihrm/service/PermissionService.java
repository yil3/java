package com.ihrm.service;


import com.ihrm.entity.system.Permission;
import com.ihrm.entity.system.RolePermission;
import com.ihrm.exception.CommonException;

import java.util.List;
import java.util.Map;

public interface PermissionService {
  void save(Map<String, Object> map) throws Exception;

  void updateById(Map<String, Object> map) throws Exception;

  void deleteById(long id) throws CommonException;

  List<Permission> findAll(Map<String, Object> map);

  Map<String,Object> findById(long id) throws Exception;

  List<Permission> findByTypeAndPid(Integer type, Long pid);

  List<RolePermission> findPermissionIdByRoleId(Long permissionId);
}
