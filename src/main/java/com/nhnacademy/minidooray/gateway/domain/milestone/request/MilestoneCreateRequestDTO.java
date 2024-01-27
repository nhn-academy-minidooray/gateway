package com.nhnacademy.minidooray.gateway.domain.milestone.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneCreateRequestDTO {
  private Long projectId;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
}
