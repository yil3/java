package com.ihrm.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pe_role_permission")
public class RolePermission {

  @JsonSerialize(using = ToStringSerializer.class)
  private Long roleId;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long permissionId;
}
