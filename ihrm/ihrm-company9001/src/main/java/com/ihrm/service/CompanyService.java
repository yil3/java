package com.ihrm.service;

import com.ihrm.entity.company.Company;

import java.util.List;

public interface CompanyService {

  void save (Company company);

  List<Company> findAll();

  void updateById(Company company);

  Company findById(long id);

  void deleteById(long id);


}
