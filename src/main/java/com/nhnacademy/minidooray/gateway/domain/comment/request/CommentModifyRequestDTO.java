package com.nhnacademy.minidooray.gateway.domain.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModifyRequestDTO {
  String owner;
  String content;
}
