package com.nhnacademy.minidooray.gateway.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginDTO {
  private String id;
  private String password;
}
