package com.ihrm.service.impl;

import com.ihrm.dao.PermissionPointDao;
import com.ihrm.entity.system.PermissionPoint;
import com.ihrm.service.PermissionPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionPointServiceImpl implements PermissionPointService {

  @Resource
  private PermissionPointDao permissionPointDao;
  @Override
  public void save(PermissionPoint entity) {
    permissionPointDao.insert(entity);
  }

  @Override
  public void updateById(PermissionPoint entity) {
    permissionPointDao.updateById(entity);
  }

  @Override
  public void deleteById(Long id) {
    permissionPointDao.deleteById(id);
  }

  @Override
  public PermissionPoint findById(Long id) {
    return permissionPointDao.selectById(id);
  }

  @Override
  public List<PermissionPoint> findAll() {
    return permissionPointDao.selectList(null);
  }
}
