package com.ihrm.controller;


import com.ihrm.entity.Result;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.company.Company;
import com.ihrm.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController {
  @Resource
  private CompanyService companyService;

  @GetMapping
  public Result findAll(){
    List<Company> companies = companyService.findAll();
    return new Result(ResultCode.SUCCESS, companies);
  }

  @PostMapping
  public Result save(@RequestBody Company company){
    companyService.save(company);
    return new Result(ResultCode.SUCCESS);
  }

  @PutMapping("/{id}")
  public Result updateById(@PathVariable("id") long id, @RequestBody Company company){
    company.setId(id);
    companyService.updateById(company);
    return new Result(ResultCode.SUCCESS);
  }

  @GetMapping("/{id}")
  public Result findById(@PathVariable long id){
    Company company = companyService.findById(id);
    return new Result(ResultCode.SUCCESS, company);
  }

  @DeleteMapping("/{id}")
  public Result deleteById(@PathVariable long id) {
    companyService.deleteById(id);
    return new Result(ResultCode.SUCCESS);
  }
}
