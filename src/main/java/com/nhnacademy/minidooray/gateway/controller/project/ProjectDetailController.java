package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectDetailController {
  private final ProjectService projectService;
  @GetMapping("/{projectId}")
  public String getProjectDetail(@PathVariable long projectId, Model model) {
    Optional<ProjectInfoResponseDTO> project = projectService.readProject(new ProjectInfoRequestDTO(projectId));
    model.addAttribute("projectInfo", project);
    return "project/detail";
  }
}
