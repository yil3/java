package com.universe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FUser {
  private Integer id;
  private String username;
  private String password;
  private String perms;
}