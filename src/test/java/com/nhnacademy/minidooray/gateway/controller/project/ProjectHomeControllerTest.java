package com.nhnacademy.minidooray.gateway.controller.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ProjectHomeControllerTest {
  private MockMvc mockMvc;
  private ProjectService projectService;
  private MockHttpSession session;

  @BeforeEach
  void setUp() {
    projectService = mock(ProjectService.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new ProjectHomeController(projectService)).build();
    session = mock(MockHttpSession.class);
    when(session.getAttribute("ACCOUNT_ID")).thenReturn("accountTestId");
  }
  @Test
  void getProjectHomePage() throws Exception{
    List<ProjectListInfoResponseDTO> projectList = List.of(
        new ProjectListInfoResponseDTO(1L, "프로젝트 1", "테스트 프로젝트", "활성", "admin"),
        new ProjectListInfoResponseDTO(2L, "프로젝트 2", "테스트 프로젝트", "활성", "admin"),
        new ProjectListInfoResponseDTO(3L, "프로젝트 3", "테스트 프로젝트", "활성", "admin"),
        new ProjectListInfoResponseDTO(4L, "프로젝트 4", "테스트 프로젝트", "활성", "admin")
    );

    when(projectService.readProjectList(anyString())).thenReturn(projectList);

    mockMvc.perform(MockMvcRequestBuilders.get("/project/home")
        .session(session))
        .andExpect(model().attribute("projectList", projectList))
        .andExpect(view().name("project/home"));
  }
}