package com.nhnacademy.minidooray.gateway.domain.account.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponseDTO {
  private String id;
  private String name;
  private String email;
  private String status;
  // todo status enum
}
