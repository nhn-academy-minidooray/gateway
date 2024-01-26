package com.nhnacademy.minidooray.gateway.domain.account.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusInfoResponseDTO {
  private String id;
  private String status;
}
