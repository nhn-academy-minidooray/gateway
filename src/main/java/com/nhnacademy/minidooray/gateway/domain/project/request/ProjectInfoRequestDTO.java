package com.nhnacademy.minidooray.gateway.domain.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//todo 어떤 annotation만 들어가면 된느지 알아보기
public class ProjectInfoRequestDTO {
  private long projectId;
}
