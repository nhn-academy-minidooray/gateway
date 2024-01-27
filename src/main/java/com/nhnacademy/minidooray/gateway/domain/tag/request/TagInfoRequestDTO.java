package com.nhnacademy.minidooray.gateway.domain.tag.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagInfoRequestDTO {
  private Long projectId;
  private long taskId;
}
