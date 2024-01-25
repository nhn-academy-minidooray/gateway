package com.nhnacademy.minidooray.gateway.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  private String id;
  private String password;
  private String name;
  private String email;
  private String status;
}
