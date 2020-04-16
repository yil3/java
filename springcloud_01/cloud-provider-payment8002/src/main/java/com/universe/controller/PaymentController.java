package com.universe.controller;


import com.universe.entity.CommonResult;
import com.universe.entity.Payment;
import com.universe.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentController {

  @Resource
  private PaymentService paymentService;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/payment/findAll")
  public CommonResult findAll() {
    List<Payment> payments = paymentService.findAll();
    if (!payments.isEmpty()) return new CommonResult(200, "查询所有serverPort"+serverPort, payments);
    return new CommonResult(444, "查询失败");
  }

  @PostMapping("/payment/create")
  public CommonResult<Integer> create(@RequestBody Payment payment) {
    if (!payment.getSerial().isEmpty()) {
      int result = paymentService.create(payment);
      if (result != 0) return new CommonResult<Integer>(200, "插入成功serverPort"+serverPort, result);
    }
    return new CommonResult<Integer>(444, "内容不能为空！");
  }

  @GetMapping("/payment/find/{id}")
  public CommonResult<Payment> findById(@PathVariable long id){
    Payment payment = paymentService.findById(id);
    if (payment != null ) return new CommonResult<Payment>(200,"查询id="+id,payment);
    return new CommonResult<Payment>(444,"id=" + id + ":不存在");
  }

}
