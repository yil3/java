package com.ihrm.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("pe_user_role")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long userId;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long roleId;
}
