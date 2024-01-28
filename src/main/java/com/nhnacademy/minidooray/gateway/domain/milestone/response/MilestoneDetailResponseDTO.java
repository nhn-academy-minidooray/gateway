package com.nhnacademy.minidooray.gateway.domain.milestone.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneDetailResponseDTO {
  Long id;
  String name;
  LocalDate startDate;
  LocalDate endDate;
  String overOrNot;
}
