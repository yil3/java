package com.ihrm.service.impl;

import com.ihrm.dao.PermissionApiDao;
import com.ihrm.entity.system.PermissionApi;
import com.ihrm.service.PermissionApiService;
import com.ihrm.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionApiServiceImpl implements PermissionApiService {
  @Resource
  private PermissionApiDao permissionApiDao;
  @Override
  public void save(PermissionApi entity) {
    permissionApiDao.insert(entity);
  }

  @Override
  public void updateById(PermissionApi entity) {
    permissionApiDao.updateById(entity);
  }

  @Override
  public void deleteById(Long id) {
    permissionApiDao.deleteById(id);
  }

  @Override
  public PermissionApi findById(Long id) {
    return permissionApiDao.selectById(id);
  }

  @Override
  public List<PermissionApi> findAll() {
    return permissionApiDao.selectList(null);
  }
}
