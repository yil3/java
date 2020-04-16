package com.universe.service;

import com.universe.dao.PaymentDao;
import com.universe.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Resource
  private PaymentDao paymentDao;

  @Override
  public List<Payment> findAll() {
    return paymentDao.selectList(null);
  }

  @Override
  public int create(Payment payment) {
    return paymentDao.insert(payment);
  }

  @Override
  public Payment findById(long id) {
    return paymentDao.selectById(id);
  }
}
