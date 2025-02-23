package com.alibou.banking.role.service;


import com.alibou.banking.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
  private Long id;
  private String  name  ;
  private List<UserResponse> users;
}
