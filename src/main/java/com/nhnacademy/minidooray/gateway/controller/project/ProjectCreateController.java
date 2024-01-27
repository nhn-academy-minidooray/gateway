package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.service.project.ProjectService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/project/create")
@RequiredArgsConstructor
public class ProjectCreateController {
  private final ProjectService projectService;
  @GetMapping
  public String getProjectCreatePage(@SessionAttribute(name = "ACCOUNT_ID") String accountId, Model model) {
    model.addAttribute("accountId", accountId);
    return "project/create";
  }

  @PostMapping
  public String postProject(@Valid @ModelAttribute ProjectCreateRequestDTO projectCreateRequestDTO) {
    log.debug("postProject(): requestDTO name -> {}, detail -> {}, admin -> {}", projectCreateRequestDTO.getName(), projectCreateRequestDTO.getDetail(), projectCreateRequestDTO.getAdminId());
    if(projectService.createProject(projectCreateRequestDTO)) {
      log.debug("postProject(): project created");
      return "redirect:/project/home";
    }
    log.debug("postProject(): project created failed");
    return "redirect:/project/home";
  }
}
