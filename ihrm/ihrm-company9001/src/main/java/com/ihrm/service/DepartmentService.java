package com.ihrm.service;

import com.ihrm.entity.company.Department;

import java.util.List;

public interface DepartmentService {
  void save(Department department);

  void update(Department department);

  Department findById(long id);

  List<Department> findAll(long companyId);

  void deleteById(long id);
}
