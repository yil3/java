package com.universe.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.universe.handler.BlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SentinelController {


  @GetMapping("/test1")
  public String test(){
    return "test1" + UUID.randomUUID().toString();
  }

  @GetMapping("/test2")
  public String test2(){
    return "test2" + UUID.randomUUID().toString();
  }

  @GetMapping("/test3")
  @SentinelResource(value = "test3", blockHandler = "test3Handler")
  public String test3(String p1 ,String p2){
    return "test3" + p1+ p2 +"-------------" + UUID.randomUUID().toString();
  }
  public String test3Handler(String p1, String p2, BlockException exception){
    return "test3Handler" + p1 + p2;
  }

  @GetMapping("/test4")
  @SentinelResource(value = "test4", blockHandlerClass = BlockHandler.class, blockHandler = "testBlockHandler2")
  public String test4(){
    return "test4 " + UUID.randomUUID().toString();
  }
}
