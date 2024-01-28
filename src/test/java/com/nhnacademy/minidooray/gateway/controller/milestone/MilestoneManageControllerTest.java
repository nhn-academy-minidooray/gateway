package com.nhnacademy.minidooray.gateway.controller.milestone;

import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MilestoneManageControllerTest {

  @InjectMocks
  private MilestoneManageController milestoneManageController;

  @Mock
  private MilestoneService milestoneService;

  @Mock
  private Model model;

  @Mock
  private RedirectAttributes redirectAttributes;

  @Test
  void testPostMilestoneInProjectSuccess() {
    // Given
    MilestoneRequestDTO milestoneRequestDTO = new MilestoneRequestDTO();
    milestoneRequestDTO.setProjectId(1L);

    // When
    String result = milestoneManageController.postMilestoneInProject(milestoneRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + milestoneRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("info"), anyString());
  }

  @Test
  void testPostMilestoneInProjectFailure() {
    // Given
    MilestoneRequestDTO milestoneRequestDTO = new MilestoneRequestDTO();
    milestoneRequestDTO.setProjectId(1L);

    when(milestoneService.createMilestoneInProject(any())).thenReturn(true);

    // When
    String result = milestoneManageController.postMilestoneInProject(milestoneRequestDTO, redirectAttributes);

    // Then
    assertEquals("redirect:/project/" + milestoneRequestDTO.getProjectId(), result);
    verify(redirectAttributes, times(1)).addFlashAttribute(eq("error"), anyString());
  }
}
