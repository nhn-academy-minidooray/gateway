package com.nhnacademy.minidooray.gateway.domain.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginRequestDTO {
  @NotNull
  @NotBlank
  @Length(min=4, max = 10)
  private String id;
  @NotNull
  @NotBlank
  @Length(min = 4, max = 20)
  private String password;
  // todo validation 규칙 정하기
}
