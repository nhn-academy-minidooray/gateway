package com.nhnacademy.minidooray.gateway.service.comment;

import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import java.util.List;

public interface CommentService {
  List<CommentResponseDTO> readCommentsByTaskId(Long taskId);
  boolean createCommentInTask(CommentCreateRequestDTO commentCreateRequestDTO);
  boolean updateCommentInTask(Long commentId, CommentModifyRequestDTO commentModifyRequestDTO);
}
