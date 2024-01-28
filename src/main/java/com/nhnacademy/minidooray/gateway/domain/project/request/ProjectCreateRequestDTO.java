package com.nhnacademy.minidooray.gateway.domain.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateRequestDTO {
  private String name;
  private String detail;
  private String adminId;
}
