package com.nhnacademy.minidooray.gateway.controller.task;
import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInTaskRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.service.comment.CommentService;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
import com.nhnacademy.minidooray.gateway.service.tag.TagService;
import com.nhnacademy.minidooray.gateway.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskDetailControllerTest {

  @InjectMocks
  private TaskDetailController taskDetailController;

  @Mock
  private TaskService taskService;

  @Mock
  private TagService tagService;

  @Mock
  private MilestoneService milestoneService;

  @Mock
  private CommentService commentService;

  @Mock
  private Model model;

  @Test
  void testGetTaskDetailPage() {
    // Given
    Long projectId = 1L;
    Long taskId = 2L;

    TaskDetailResponseDTO taskDetailResponseDTO = new TaskDetailResponseDTO();
    when(taskService.readTaskByTaskId(anyLong())).thenReturn(Optional.of(taskDetailResponseDTO));

    List<TagInfoResponseDTO> tagList = Arrays.asList(new TagInfoResponseDTO());
    when(tagService.readTagListInTask(any())).thenReturn(tagList);

    MilestoneInfoResponseDTO milestoneInfoResponseDTO = new MilestoneInfoResponseDTO();
    when(milestoneService.readMilestoneInTask(anyLong())).thenReturn(Optional.of(milestoneInfoResponseDTO));

    List<CommentResponseDTO> commentList = Arrays.asList(new CommentResponseDTO());
    when(commentService.readCommentsByTaskId(anyLong())).thenReturn(commentList);

    List<MilestoneInfoResponseDTO> milestoneList = Arrays.asList(new MilestoneInfoResponseDTO());
    when(milestoneService.readMilestoneListInProject(anyLong())).thenReturn(milestoneList);

    // When
    String result = taskDetailController.getTaskDetailPage(projectId, taskId, model);

    // Then
    assertEquals("project/task", result);
    verify(model, times(1)).addAttribute(eq("taskInfo"), eq(taskDetailResponseDTO));
    verify(model, times(1)).addAttribute(eq("tagList"), eq(tagList));
    verify(model, times(1)).addAttribute(eq("milestone"), eq(milestoneInfoResponseDTO));
    verify(model, times(1)).addAttribute(eq("milestoneList"), eq(milestoneList));
    verify(model, times(1)).addAttribute(eq("commentList"), eq(commentList));
    verify(model, times(1)).addAttribute(eq("projectId"), eq(projectId));
  }
}
