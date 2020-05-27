package com.ihrm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.entity.system.Role;

import java.util.List;
import java.util.Set;

public interface RoleService extends BaseService<Role>{
  List<Role> findAll(Long companyId);

  Page<Role> findByPage(long companyId, int page, int size);

  void assignPerms(String roleId, List<String> permIds);

  void deletePerms(String roleId, List<String> permIds);

  void deleteRolePermission(Long roleId);

  Set<String> findPermissionsByRoleId(Long roleId);

}
