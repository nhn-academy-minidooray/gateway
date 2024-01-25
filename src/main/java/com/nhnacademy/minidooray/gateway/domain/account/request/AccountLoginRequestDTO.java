package com.nhnacademy.minidooray.gateway.domain.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginRequestDTO {
  @NotNull
  @NotBlank
  private String id;
  @NotNull
  @NotBlank
  private String password;
  // todo validation 규칙 정하기
}
