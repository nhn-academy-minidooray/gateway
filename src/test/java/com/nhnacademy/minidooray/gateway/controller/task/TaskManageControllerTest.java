package com.nhnacademy.minidooray.gateway.controller.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskManageControllerTest {

  @InjectMocks
  private TaskManageController taskManageController;

  @Mock
  private TaskService taskService;

  @Mock
  private Model model;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testPostTaskInProject() {
    // Given
    TaskCreateRequestDTO taskCreateRequestDTO = new TaskCreateRequestDTO();
    taskCreateRequestDTO.setProjectId(1L);

    when(taskService.createTaskInProject(any())).thenReturn(true);

    // When
    String result = taskManageController.postTaskInProject(taskCreateRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/1", result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), eq("태스크 추가가 완료되었습니다"));
  }

  @Test
  void testModifyTaskInProject() {
    // Given
    Long taskId = 1L;
    TaskCreateRequestDTO taskModifyRequestDTO = new TaskCreateRequestDTO();
    taskModifyRequestDTO.setProjectId(1L);

    when(taskService.updateTask(anyLong(), any())).thenReturn(true);

    // When
    String result = taskManageController.modifyTaskInProject(taskId, taskModifyRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/1/1", result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), eq("태스크 수정 성공"));
  }

  @Test
  void testDeleteTaskInProject() {
    // Given
    Long taskId = 1L;
    Long projectId = 1L;

    when(taskService.deleteTesk(any())).thenReturn(true);

    // When
    String result = taskManageController.deleteTaskInProject(taskId, projectId, redirectAttributes);

    // Then
    assertEquals("redirect:/project/1", result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), eq("태스크 삭제 성공"));
  }
}
