package com.nhnacademy.minidooray.gateway.service.comment;

import com.nhnacademy.minidooray.gateway.adaptor.comment.CommentAdaptor;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
  private final CommentAdaptor commentAdaptor;
  @Override
  public List<CommentResponseDTO> readCommentsByTaskId(Long taskId) {
    return commentAdaptor.selectCommentListByTaskId(taskId);
  }

  @Override
  public boolean createCommentInTask(CommentCreateRequestDTO commentCreateRequestDTO) {
    return commentAdaptor.insertCommentInProject(commentCreateRequestDTO);
  }

  @Override
  public boolean updateCommentInTask(Long commentId, CommentModifyRequestDTO commentModifyRequestDTO) {
    return commentAdaptor.updateCommentInProject(commentId, commentModifyRequestDTO);
  }
}
