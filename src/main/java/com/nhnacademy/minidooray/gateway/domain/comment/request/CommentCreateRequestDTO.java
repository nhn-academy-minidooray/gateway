package com.nhnacademy.minidooray.gateway.domain.comment.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequestDTO {
  private Long taskId;
  private String owner;
  private String content;
}
