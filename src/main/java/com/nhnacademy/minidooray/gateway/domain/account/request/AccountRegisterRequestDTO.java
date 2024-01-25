package com.nhnacademy.minidooray.gateway.domain.account.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterRequestDTO {
  @NotNull
  @NotBlank
  private String id;
  @NotNull
  @NotBlank
  private String password;
  @NotNull
  @NotBlank
  private String name;
  @NotNull
  @NotBlank
  @Email
  private String email;
  // todo validation set
}
