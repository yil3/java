package com.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihrm.dao.*;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.system.*;
import com.ihrm.exception.CommonException;
import com.ihrm.service.PermissionService;
import com.ihrm.utils.BeanMapUtils;
import com.ihrm.utils.IdWorker;
import com.ihrm.utils.PermissionConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

  @Resource
  private PermissionDao permissionDao;
  @Resource
  private PermissionMenuDao permissionMenuDao;
  @Resource
  private PermissionPointDao permissionPointDao;
  @Resource
  private PermissionApiDao permissionApiDao;
  @Resource
  private RolePermissionDao rolePermissionDao;


  @Override
  public Map<String, Object> findById(long id) throws Exception {
    Permission permission = permissionDao.selectById(id);
    if (permission == null){
      throw new Exception("id不存在!");
    }

    Integer type = permission.getType();
    Object obj = null;
    switch (type) {
      case PermissionConstants.PY_MENU:
        obj = permissionMenuDao.selectById(id);
        break;
      case PermissionConstants.PY_POINT:
        obj = permissionPointDao.selectById(id);
        break;
      case PermissionConstants.PY_API:
        obj = permissionApiDao.selectById(id);
        break;
      default:
        throw new CommonException(ResultCode.FAIL);
    }

    Map<String, Object> map = BeanMapUtils.beanToMap(obj);
    map.remove("id");

    map.put("name", permission.getName());
    map.put("type", permission.getType());
    map.put("code" ,permission.getCode());
    map.put("description", permission.getDescription());
    map.put("pid", permission.getPid());
    map.put("enVisible", permission.getEnVisible());

    return map;
  }

  @Override
  public List<Permission> findByTypeAndPid(Integer type, Long pid) {
    QueryWrapper<Permission> wrapper = new QueryWrapper<>();
    wrapper.eq("type", type).and(e -> e.eq("pid", pid));
    return permissionDao.selectList(wrapper);
  }

  @Override
  public void save(Map<String, Object> map) throws Exception {
    Permission permission = BeanMapUtils.mapToBean(map, Permission.class);
    long id = new IdWorker().nextId();
    permission.setId(id);

    int type = permission.getType();

    switch (type) {
      case PermissionConstants.PY_MENU:
        PermissionMenu permissionMenu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
        permissionMenu.setId(id);
        permissionMenuDao.insert(permissionMenu);
        break;
      case PermissionConstants.PY_API:
        PermissionApi permissionApi = BeanMapUtils.mapToBean(map, PermissionApi.class);
        permissionApi.setId(id);
        permissionApiDao.insert(permissionApi);
        break;
        case PermissionConstants.PY_POINT:
          PermissionPoint permissionPoint = BeanMapUtils.mapToBean(map, PermissionPoint.class);
          permissionPoint.setId(id);
          permissionPointDao.insert(permissionPoint);
          break;
      default:
        throw new CommonException(ResultCode.FAIL);
    }

    permissionDao.insert(permission);
  }

  @Override
  public void updateById(Map<String, Object> map) throws Exception {
    Permission perm = BeanMapUtils.mapToBean(map, Permission.class);

    Permission permission = permissionDao.selectById(perm.getId());

    // permission.setCode(perm.getCode());
    // permission.setDescription(perm.getDescription());
    // permission.setName(perm.getName());
    // permission.setEnVisible(perm.getEnVisible());

    int type = permission.getType();
    switch (type) {
      case PermissionConstants.PY_MENU:
        PermissionMenu permissionMenu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
        permissionMenu.setId(perm.getId());
        permissionMenuDao.updateById(permissionMenu);
        break;
      case PermissionConstants.PY_API:
        PermissionApi permissionApi = BeanMapUtils.mapToBean(map, PermissionApi.class);
        permissionApi.setId(perm.getId());
        permissionApiDao.updateById(permissionApi);
        break;
      case PermissionConstants.PY_POINT:
        PermissionPoint permissionPoint = BeanMapUtils.mapToBean(map, PermissionPoint.class);
        permissionPoint.setId(perm.getId());
        permissionPointDao.updateById(permissionPoint);
        break;
      default:
        throw new CommonException(ResultCode.FAIL);
    }

    permissionDao.updateById(perm);

  }

  @Override
  public void deleteById(long id) throws CommonException {
    Permission permission = permissionDao.selectById(id);
    Integer type = permission.getType();

    switch (type) {
      case PermissionConstants.PY_MENU:
        permissionMenuDao.deleteById(id);
        break;
      case PermissionConstants.PY_API:
        permissionApiDao.deleteById(id);
        break;
      case PermissionConstants.PY_POINT:
        permissionPointDao.deleteById(id);
        break;
      default:
        throw new CommonException(ResultCode.FAIL);
    }
  permissionDao.deleteById(id);

  }

  @Override
  public List<Permission> findAll(Map<String, Object> map) {
    QueryWrapper<Permission> wrapper = new QueryWrapper<>();
    if (!StringUtils.isEmpty(map.get("pid"))) wrapper.eq("pid", map.get("pid"));
    if (!StringUtils.isEmpty(map.get("enVisible"))) wrapper.eq("en_visible", map.get("enVisible"));
    if (!StringUtils.isEmpty(map.get("type"))){
      String type = (String) map.get("type");
      if ("0".equals(type)) {
        wrapper.in("type",1,2);
      }else {
        wrapper.in("type", Integer.parseInt(type));
      }
    }
    return permissionDao.selectList(wrapper);
  }

  @Override
  public List<RolePermission> findPermissionIdByRoleId(Long permissionId) {
    return rolePermissionDao.selectList(new QueryWrapper<RolePermission>().eq("permission_id", permissionId));
  }


}
