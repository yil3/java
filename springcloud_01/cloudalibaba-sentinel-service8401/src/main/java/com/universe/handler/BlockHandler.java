package com.universe.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import java.util.UUID;

public class BlockHandler {

  public static String testBlockHandler1(BlockException exception){
    return "testBlockHandler-----1" + UUID.randomUUID().toString();
  }

  public static String testBlockHandler2(BlockException exception){
    return "testBlockHandler-----2" + UUID.randomUUID().toString();
  }
}
