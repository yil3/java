package com.ihrm.service.impl;

import com.ihrm.dao.PermissionMenuDao;
import com.ihrm.entity.system.PermissionMenu;
import com.ihrm.service.PermissionMenuService;
import com.ihrm.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class PermissionMenuServiceImpl implements PermissionMenuService {
  @Resource
  private PermissionMenuDao permissionMenuDao;
  @Override
  public void save(PermissionMenu entity) {
    permissionMenuDao.insert(entity);
  }

  @Override
  public void updateById(PermissionMenu entity) {
    permissionMenuDao.updateById(entity);
  }

  @Override
  public void deleteById(Long id) {
    permissionMenuDao.deleteById(id);
  }

  @Override
  public PermissionMenu findById(Long id) {
    return permissionMenuDao.selectById(id);
  }

  @Override
  public List<PermissionMenu> findAll() {
    return permissionMenuDao.selectList(null);
  }
}
