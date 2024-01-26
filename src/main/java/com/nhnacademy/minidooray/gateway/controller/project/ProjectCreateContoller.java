package com.nhnacademy.minidooray.gateway.controller.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/create")
public class ProjectCreateContoller {
  @GetMapping
  public String getProjectCreatePage() {
    return "project/create";
  }

  @PostMapping
  public String postProject() {
    // todo something
    return "redirect:/project/home";
  }
}
