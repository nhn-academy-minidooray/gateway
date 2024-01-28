package com.nhnacademy.minidooray.gateway.adaptor.comment;

import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import java.util.List;

public interface CommentAdaptor {
  List<CommentResponseDTO> selectCommentListByTaskId(Long taskId);
  boolean insertCommentInProject(CommentCreateRequestDTO commentCreateRequestDTO);
  boolean updateCommentInProject(Long commentId, CommentModifyRequestDTO commentModifyRequestDTO);
}
