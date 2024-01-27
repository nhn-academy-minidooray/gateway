package com.nhnacademy.minidooray.gateway.domain.task.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequestDTO {
  private Long projectId;
  private String name;
  private Long milestoneId;
  private List<Long> tagList;
  private String detail;
}
