package com.nhnacademy.minidooray.gateway.controller.project;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectCreateControllerTest {

  @InjectMocks
  private ProjectCreateController projectCreateController;

  @Mock
  private ProjectService projectService;

  @Mock
  private Model model;

  @Test
  void testGetProjectCreatePage() {
    // Given
    String accountId = "testAccountId";

    // When
    String result = projectCreateController.getProjectCreatePage(accountId, model);

    // Then
    assertEquals("project/create", result);
    verify(model, times(1)).addAttribute(eq("accountId"), eq(accountId));
  }

  @Test
  void testPostProjectSuccess() {
    // Given
    ProjectCreateRequestDTO projectCreateRequestDTO = new ProjectCreateRequestDTO();
    projectCreateRequestDTO.setName("Test Project");
    projectCreateRequestDTO.setDetail("Test Project Detail");
    projectCreateRequestDTO.setAdminId("testAdminId");

    // When
    String result = projectCreateController.postProject(projectCreateRequestDTO);

    // Then
    assertEquals("redirect:/project/home", result);
    verify(projectService, times(1)).createProject(any());
  }

  @Test
  void testPostProjectFailure() {
    // Given
    ProjectCreateRequestDTO projectCreateRequestDTO = new ProjectCreateRequestDTO();
    projectCreateRequestDTO.setName("Test Project");
    projectCreateRequestDTO.setDetail("Test Project Detail");
    projectCreateRequestDTO.setAdminId("testAdminId");

    when(projectService.createProject(any())).thenReturn(false);

    // When
    String result = projectCreateController.postProject(projectCreateRequestDTO);

    // Then
    assertEquals("redirect:/project/home", result);
    verify(projectService, times(1)).createProject(any());
  }
}
