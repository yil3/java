package com.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.dao.RoleDao;
import com.ihrm.dao.RolePermissionDao;
import com.ihrm.entity.system.Permission;
import com.ihrm.entity.system.Role;
import com.ihrm.entity.system.RolePermission;
import com.ihrm.service.PermissionService;
import com.ihrm.service.RoleService;
import com.ihrm.utils.IdWorker;
import com.ihrm.utils.PermissionConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
  @Resource
  private RoleDao roleDao;
  @Resource
  IdWorker idWorker;
  @Resource
  private RolePermissionDao rolePermissionDao;
  @Resource
  private PermissionService permissionService;

  @Override
  public void save(Role entity) {
//    entity.setId(new IdWorker().nextId());
    entity.setId(idWorker.nextId());
    roleDao.insert(entity);
  }

  @Override
  public void updateById(Role entity) {
    roleDao.updateById(entity);
  }

  @Override
  public void deleteById(Long id) {
    roleDao.deleteById(id);
  }

  @Override
  public Role findById(Long id) {
    return roleDao.selectById(id);
  }

  @Override
  public List<Role> findAll(Long companyId) {
    QueryWrapper<Role> wrapper = new QueryWrapper<>();
    wrapper.eq("company_id",companyId);
    return roleDao.selectList(wrapper);
  }
  @Override
  public List<Role> findAll() {
    return roleDao.selectList(null);
  }

  @Override
  public Page<Role> findByPage(long companyId, int page, int size){

    Page<Role> rolePage = new Page<>(page, size);
    QueryWrapper<Role> wrapper = new QueryWrapper<>();
    wrapper.eq("company_id", companyId);
    return roleDao.selectPage(rolePage, wrapper);
  }

  @Override
  public void assignPerms(String roleId, List<String> permIds) {
    permIds.stream().distinct()
        .forEach(e -> {
          rolePermissionDao.insert(new RolePermission(Long.parseLong(roleId), Long.parseLong(e)));

          List<Permission> permissionList = permissionService.findByTypeAndPid(PermissionConstants.PY_API, Long.parseLong(e));
          if (permissionList != null) {
            permissionList.stream()
                .map(Permission::getId)
                .forEach(permId -> rolePermissionDao.insert(new RolePermission(Long.parseLong(roleId), permId)));
          }
        });
  }

  @Override
  public void deletePerms(String roleId, List<String> permIds) {
    permIds.stream().distinct().map(id -> Long.parseLong(id.trim()))
        .forEach(permId -> {
          QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
          rolePermissionQueryWrapper.eq("role_id", Long.parseLong(roleId)).and(and -> and.eq("permission_id", permId));
          rolePermissionDao.delete(rolePermissionQueryWrapper);

          List<Permission> permissionList = permissionService.findByTypeAndPid(PermissionConstants.PY_API, permId);
          if (permissionList != null) {
            permissionList.stream().map(Permission::getId)
                .forEach(permApiId -> {
                  QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
                  wrapper.eq("role_id", Long.parseLong(roleId)).and(and -> and.eq("permission_id", permApiId));
                  rolePermissionDao.delete(wrapper);
                });
          }
        });
  }

  @Override
  public void deleteRolePermission(Long roleId){
    QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("role_id", roleId);
    rolePermissionDao.delete(queryWrapper);
  }

  @Override
  public Set<String> findPermissionsByRoleId(Long roleId) {
    QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("role_id", roleId);
    return rolePermissionDao.selectList(queryWrapper).stream()
                            .map(RolePermission::getPermissionId)
                            .map(String::valueOf)
                            .collect(Collectors.toSet());
  }


}
