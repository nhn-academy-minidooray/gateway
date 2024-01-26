package com.nhnacademy.minidooray.gateway.domain.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
  private long id;
  private String name;
  private String detail;
  private String status;
  private String adminId;
}
