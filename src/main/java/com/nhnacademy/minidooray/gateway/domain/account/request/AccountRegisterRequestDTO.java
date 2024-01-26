package com.nhnacademy.minidooray.gateway.domain.account.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterRequestDTO {
  @NotNull
  @NotBlank
  @Length(min=4, max = 10)
  private String id;
  @NotNull
  @NotBlank
  @Length(min = 8, max = 20)
  private String password;
  @NotNull
  @NotBlank
  @Email
  private String email;
  @NotNull
  @NotBlank
  @Length(min = 1, max = 20)
  private String name;
  // todo validation set
}
