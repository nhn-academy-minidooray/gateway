package com.nhnacademy.minidooray.gateway.domain.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectListInfoResponseDTO {
  private long projectId;
  private String name;
  private String detail;
  private String status;
}
