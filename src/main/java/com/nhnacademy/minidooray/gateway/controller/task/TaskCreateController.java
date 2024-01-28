package com.nhnacademy.minidooray.gateway.controller.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/project/task")
public class TaskCreateController {
  @PostMapping("/create")
  public String postTaskInProject(@ModelAttribute TaskCreateRequestDTO taskCreateRequestDTO) {
    return "";
    // todo
  }
}
