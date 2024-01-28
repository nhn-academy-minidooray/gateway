package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberListRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.member.response.MemberListResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInTaskRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.member.MemberService;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import com.nhnacademy.minidooray.gateway.service.tag.TagService;
import com.nhnacademy.minidooray.gateway.service.task.TaskService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectDetailController {
  private final ProjectService projectService;
  private final MemberService memberService;
  private final TaskService taskService;
  private final TagService tagService;
  private final MilestoneService milestoneService;

  @GetMapping("/{projectId}")
  public String getProjectDetail(@PathVariable long projectId, Model model, @SessionAttribute(name = "ACCOUNT_ID") String accountId) {
    Optional<ProjectInfoResponseDTO> projectWrapper = projectService.readProject(new ProjectInfoRequestDTO(projectId));
    List<MemberListResponseDTO> membersInProject = memberService.readMemberList(new MemberListRequestDTO(projectId));
    List<TaskInfoResponseDTO> tasksInProject = taskService.readTaskListInProject(new TaskInfoRequestDTO(projectId));
    List<TagInfoResponseDTO> tagsInProject = tagService.readTagListInProject(new TagListInProjectRequestDTO(projectId));
    List<MilestoneInfoResponseDTO> milestonesInProject = milestoneService.readMilestoneListInProject(projectId);
    model.addAttribute("accountId", accountId);

    log.debug("getProjectDetail(): projectId -> {}", projectWrapper.get().getId());
    if(projectWrapper.isPresent()) {
      ProjectInfoResponseDTO project = projectWrapper.get();
      model.addAttribute("projectInfo", project);
      model.addAttribute("memberList", membersInProject);
      model.addAttribute("taskList", tasksInProject);
      model.addAttribute("tagList", tagsInProject);
      model.addAttribute("milestoneList", milestonesInProject);
      log.debug("taskList -> {}", tasksInProject.size());
      log.debug("tagList -> {}", tagsInProject.size());
      log.debug("milestoneList -> {}", milestonesInProject.size());
      return "project/detail";
    }
    // error시?
    return "redirect:/project/home";
  }
}
