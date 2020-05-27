package com.ihrm.controller;


import com.ihrm.entity.Result;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.company.Company;
import com.ihrm.entity.company.Department;
import com.ihrm.entity.company.response.DeptListResult;
import com.ihrm.service.CompanyService;
import com.ihrm.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class DepartmentController {

  @Resource
  private DepartmentService departmentService;

  @Resource
  private CompanyService companyService;

  @PostMapping("/department")
  public Result save(@RequestBody Department department) {
    department.setCompanyId("1");
    departmentService.save(department);
    return new Result(ResultCode.SUCCESS);
  }

  @GetMapping("/department/{id}")
  public Result findById(@PathVariable("id") long id) {
    Department department = departmentService.findById(id);
    return new Result(ResultCode.SUCCESS,department);
  }

  @GetMapping("/department")
  public Result findAll(Long companyId) {
    long id = companyId == null ? 1L : companyId;

    Company company = companyService.findById(id);
    if (company == null) return new Result(4000,"id不存在",false);

    List<Department> departments = departmentService.findAll(company.getId());
    DeptListResult deptListResult = new DeptListResult(company, departments);

    return new Result(ResultCode.SUCCESS,deptListResult);
  }

  @PutMapping("/department/{id}")
  public Result update(@PathVariable long id, @RequestBody Department department){
    department.setId(id);
    departmentService.update(department);
    return new Result(ResultCode.SUCCESS);
  }

  @DeleteMapping("/department/{id}")
  public Result deleteById(@PathVariable("id") long id) {
    departmentService.deleteById(id);
    return new Result(ResultCode.SUCCESS);
  }


}
