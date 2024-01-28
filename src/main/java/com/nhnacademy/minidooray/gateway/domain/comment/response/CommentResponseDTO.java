package com.nhnacademy.minidooray.gateway.domain.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
  Long id;
  String owner;
  String content;
}
