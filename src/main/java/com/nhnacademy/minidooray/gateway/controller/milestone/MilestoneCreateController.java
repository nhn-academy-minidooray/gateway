package com.nhnacademy.minidooray.gateway.controller.milestone;

import com.nhnacademy.minidooray.gateway.domain.milestone.request.MilestoneRequestDTO;
import com.nhnacademy.minidooray.gateway.service.milestone.MilestoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/project/milestone")
@RequiredArgsConstructor
public class MilestoneCreateController {
  private final MilestoneService milestoneService;

  @PostMapping("/create")
  public String postMilestoneInProject(@ModelAttribute MilestoneRequestDTO milestoneRequestDTO, RedirectAttributes redirectAttributes) {
    if(milestoneService.createMilestoneInProject(milestoneRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "마일스톤을 추가하면서 오류가 발생하였습니다");
      return "redirect:/project/" + milestoneRequestDTO.getProjectId();
    }
    redirectAttributes.addFlashAttribute("info", "마일스톤 추가가 완료되었습니다");
    return "redirect:/project/" + milestoneRequestDTO.getProjectId();
  }

}
