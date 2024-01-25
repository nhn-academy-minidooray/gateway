package com.nhnacademy.minidooray.gateway.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterDTO {
  private String id;
  private String email;
  private String password;
}
