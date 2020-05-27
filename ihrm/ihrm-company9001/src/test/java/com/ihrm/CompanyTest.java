package com.ihrm;

import com.ihrm.entity.company.Company;
import com.ihrm.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CompanyTest {
  @Resource
  private CompanyService companyService;

  @Test
  public void test1(){
    Company company = new Company();
    company.setName("不要不要！！");
    companyService.save(company);
  }

}
