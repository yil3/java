package com.ihrm.service;

import com.ihrm.dao.CompanyDao;
import com.ihrm.entity.company.Company;
import com.ihrm.utils.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

  @Resource
  private CompanyDao companyDao;

  @Override
  public void save(Company company) {
    company.setId(new IdWorker().nextId());
    company.setState(1);
    company.setAuditState("0");
    companyDao.insert(company);
  }

  @Override
  public List<Company> findAll() {
    return companyDao.selectList(null);
  }

  @Override
  public void updateById(Company company) {
    companyDao.updateById(company);
  }

  @Override
  public Company findById(long id) {
    return companyDao.selectById(id);
  }

  @Override
  public void deleteById(long id) {
    companyDao.deleteById(id);
  }
}
