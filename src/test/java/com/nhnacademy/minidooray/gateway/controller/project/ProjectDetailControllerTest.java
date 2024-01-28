package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.member.MemberService;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectDetailControllerTest {

  @InjectMocks
  private ProjectDetailController projectDetailController;

  @Mock
  private ProjectService projectService;

  @Mock
  private MemberService memberService;

  @Mock
  private TaskService taskService;

  @Mock
  private TagService tagService;

  @Mock
  private MilestoneService milestoneService;

  @Mock
  private Model model;

  @Test
  void testGetProjectDetail() {
    // Given
    long projectId = 1L;
    String accountId = "testAccountId";

    ProjectInfoResponseDTO projectInfoResponseDTO = new ProjectInfoResponseDTO();
    projectInfoResponseDTO.setId(projectId);
    when(projectService.readProject(any())).thenReturn(Optional.of(projectInfoResponseDTO));

    List<MemberListResponseDTO> memberList = Arrays.asList(new MemberListResponseDTO());
    when(memberService.readMemberList(any())).thenReturn(memberList);

    List<TaskInfoResponseDTO> taskList = Arrays.asList(new TaskInfoResponseDTO());
    when(taskService.readTaskListInProject(any())).thenReturn(taskList);

    List<TagInfoResponseDTO> tagList = Arrays.asList(new TagInfoResponseDTO());
    when(tagService.readTagListInProject(any())).thenReturn(tagList);

    List<MilestoneInfoResponseDTO> milestoneList = Arrays.asList(new MilestoneInfoResponseDTO());
    when(milestoneService.readMilestoneListInProject(any())).thenReturn(milestoneList);

    // When
    String result = projectDetailController.getProjectDetail(projectId, model, accountId);

    // Then
    assertEquals("project/detail", result);
    verify(model, times(1)).addAttribute(eq("accountId"), eq(accountId));
    verify(model, times(1)).addAttribute(eq("projectInfo"), eq(projectInfoResponseDTO));
    verify(model, times(1)).addAttribute(eq("memberList"), eq(memberList));
    verify(model, times(1)).addAttribute(eq("taskList"), eq(taskList));
    verify(model, times(1)).addAttribute(eq("tagList"), eq(tagList));
    verify(model, times(1)).addAttribute(eq("milestoneList"), eq(milestoneList));
  }
}
