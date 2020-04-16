package com.universe.controller;

import com.universe.entity.CommonResult;
import com.universe.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

//  public final String PAYMENT_URL = "http://localhost:8001";
  public final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

  @Resource
  private RestTemplate restTemplate;

  @GetMapping("/consumer/payment/findAll")
  public CommonResult<Payment> findAll(){
    return restTemplate.getForObject(PAYMENT_URL + "/payment/findAll",CommonResult.class);
  }

  @GetMapping("/consumer/payment/create")
  public CommonResult<Payment> create (Payment payment){
    return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
  }

  @GetMapping("consumer/payment/find/{id}")
  public CommonResult<Payment> findById(@PathVariable long id){
    return restTemplate.getForObject(PAYMENT_URL + "/payment/find/"+id,CommonResult.class);
  }

  @GetMapping("/consumer/payment/findAll2")
  public CommonResult<Payment> findAll2(){
    ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/findAll",
            CommonResult.class);
    if (entity.getStatusCode().is2xxSuccessful()) return entity.getBody();
    return new CommonResult<>(444,"查询失败");
  }

}
