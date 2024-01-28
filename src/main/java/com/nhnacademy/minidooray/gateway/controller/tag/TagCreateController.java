package com.nhnacademy.minidooray.gateway.controller.tag;

import com.nhnacademy.minidooray.gateway.domain.tag.request.TagCreateRequestDTO;
import com.nhnacademy.minidooray.gateway.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/project/tag")
@RequiredArgsConstructor
public class TagCreateController {
  private final TagService tagService;

  @PostMapping("/create")
  public String postTagInProject(@ModelAttribute TagCreateRequestDTO tagCreateRequestDTO, RedirectAttributes redirectAttributes) {
    if(!tagService.createTag(tagCreateRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "태그를 추가하면서 오류가 발생하였습니다");
      return "redirect:/project/" + tagCreateRequestDTO.getProjectId();
    }
    redirectAttributes.addFlashAttribute("info", "태그 추가가 완료되었습니다");
    return "redirect:/project/" + tagCreateRequestDTO.getProjectId();
  }

}
