package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectListInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.project.response.ProjectListInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/project/home")
@RequiredArgsConstructor
public class ProjectHomeController {
  private final ProjectService projectService;
  @GetMapping
  public String getProjectHomePage(@SessionAttribute(name = "ACCOUNT_ID") String accountId, Model model) {
    log.debug("getProjectHomePage(): accountId -> {}", accountId);
    List<ProjectListInfoResponseDTO> projectList = projectService.readProjectList(accountId);
    log.debug("getProjectHomePage(): projectList -> {}", projectList.size());
    model.addAttribute("projectList", projectList);
    return "project/home";
  }
}
