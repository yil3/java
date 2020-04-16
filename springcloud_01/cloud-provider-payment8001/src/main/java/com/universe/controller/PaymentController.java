package com.universe.controller;


import com.universe.entity.CommonResult;
import com.universe.entity.Payment;
import com.universe.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

  @Resource
  private PaymentService paymentService;
  @Resource
  private DiscoveryClient discoveryClient;


  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/payment/findAll")
  public CommonResult<Payment> findAll() {
    List<Payment> payments = paymentService.findAll();
    if (!payments.isEmpty()) return new CommonResult(200, "查询所有serverPort"+serverPort, payments);
    return new CommonResult<Payment>(444, "查询失败");
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

  @GetMapping("/payment/discovery")
  public Object discovery(){
    List<String> services = discoveryClient.getServices();
    services.forEach(System.out::println);
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
      System.out.println(instance.getUri());
    }
    return this.discoveryClient;
  }

  @GetMapping("/payment/timeOut")
  public String timeOut(){
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return serverPort;
  }

}
