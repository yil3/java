package com.ihrm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ihrm.dao.DepartmentDao;
import com.ihrm.entity.company.Department;
import com.ihrm.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

  @Resource
  private DepartmentDao departmentDao;


  @Override
  public void save(Department department) {
    department.setId(new IdWorker().nextId());
    departmentDao.insert(department);
  }

  @Override
  public void update(Department department) {
    departmentDao.updateById(department);
  }

  @Override
  public Department findById(long id) {
    return departmentDao.selectById(id);
  }

  @Override
  public List<Department> findAll(long companyId) {


    QueryWrapper<Department> wrapper = new QueryWrapper<>();
    wrapper.eq("company_id",companyId);

    return departmentDao.selectList(wrapper);
  }

  @Override
  public void deleteById(long id) {
    departmentDao.deleteById(id);
  }
}
