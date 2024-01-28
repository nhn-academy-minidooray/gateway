package com.nhnacademy.minidooray.gateway.controller.task;

import com.nhnacademy.minidooray.gateway.domain.comment.response.CommentResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.milestone.response.MilestoneInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.request.TagListInTaskRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.tag.response.TagInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.domain.task.response.TaskDetailResponseDTO;
import com.nhnacademy.minidooray.gateway.service.comment.CommentService;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
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

@Slf4j
@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class TaskDetailController {
  private final TaskService taskService;
  private final TagService tagService;
  private final MilestoneService milestoneService;
  private final CommentService commentService;

  @GetMapping("/{projectId}/{taskId}")
  public String getTaskDetailPage(@PathVariable(name = "projectId") Long projectId, @PathVariable(name = "taskId") Long taskId, Model model) {
    Optional<TaskDetailResponseDTO> taskWrapper = taskService.readTaskByTaskId(taskId);
    List<TagInfoResponseDTO> tagList = tagService.readTagListInTask(new TagListInTaskRequestDTO(taskId));
    MilestoneInfoResponseDTO milestone =  milestoneService.readMilestoneInTask(taskId).orElse(null);
    List<CommentResponseDTO> commentList = commentService.readCommentsByTaskId(taskId);
    List<MilestoneInfoResponseDTO> milestoneList = milestoneService.readMilestoneListInProject(projectId);

    if(taskWrapper.isPresent()) {
      TaskDetailResponseDTO task = taskWrapper.get();
      model.addAttribute("taskInfo", task);
      model.addAttribute("tagList", tagList);
      model.addAttribute("milestone", milestone);
      model.addAttribute("milestoneList", milestoneList);
      model.addAttribute("commentList", commentList);
      model.addAttribute("projectId", projectId);
      return "project/task";
    }
    return "redirect:/project/home";
  }
}
