package com.nhnacademy.minidooray.gateway.domain.task.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfoResponseDTO {
  private Long id;
  private String name;
  private String detail;
  private List<String> tagList;
  private String milestoneId;
}
