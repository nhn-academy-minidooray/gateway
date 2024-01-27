package com.nhnacademy.minidooray.gateway.domain.member.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAddProjectRequestDTO {
  private Long projectId;
  private String accountId;
}
