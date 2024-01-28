package com.nhnacademy.minidooray.gateway.controller.task;

import com.nhnacademy.minidooray.gateway.domain.task.request.TaskCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.task.request.TaskDeleteRequestDTO;
import com.nhnacademy.minidooray.gateway.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/project/task")
@RequiredArgsConstructor
public class TaskManageController {
  private final TaskService taskService;
  @PostMapping("/create")
  public String postTaskInProject(@ModelAttribute TaskCreateRequestDTO taskCreateRequestDTO, RedirectAttributes redirectAttributes) {
    if(!taskService.createTaskInProject(taskCreateRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "태스크를 추가하면서 오류가 발생하였습니다");
      return "redirect:/project/" + taskCreateRequestDTO.getProjectId();
    }
    redirectAttributes.addFlashAttribute("info", "태스크 추가가 완료되었습니다");
    return "redirect:/project/" + taskCreateRequestDTO.getProjectId();
  }

  @PostMapping("/modify")
  public String modifyTaskInProject(@RequestParam(name = "taskId") Long taskId, @ModelAttribute TaskCreateRequestDTO taskModifyRequestDTO, RedirectAttributes redirectAttributes) {
    String redirectUrl = "redirect:/project/" + taskModifyRequestDTO.getProjectId() + "/" + taskId;
    if(!taskService.updateTask(taskId, taskModifyRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "태스크 수정 실패");
      return redirectUrl;
    }
    redirectAttributes.addFlashAttribute("info", "태스크 수정 성공");
    return redirectUrl;
  }

  @GetMapping("/delete/{taskId}")
  public String deleteTaskInProject(@PathVariable(name = "taskId") Long taskId, @RequestParam(name = "projectId") Long projectId, RedirectAttributes redirectAttributes) {
    if(!taskService.deleteTesk(new TaskDeleteRequestDTO(taskId))) {
      redirectAttributes.addFlashAttribute("error", "태스크 삭제 실패");
      return "redirect:/project/" + projectId;
    }
    redirectAttributes.addFlashAttribute("info", "태스크 삭제 성공");
    return "redirect:/project/" + projectId;
  }
}
