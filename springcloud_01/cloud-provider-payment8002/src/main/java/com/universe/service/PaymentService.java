package com.universe.service;

import com.universe.entity.Payment;

import java.util.List;

public interface PaymentService {
  List<Payment> findAll();
  int create(Payment payment);
  Payment findById(long id);
}
