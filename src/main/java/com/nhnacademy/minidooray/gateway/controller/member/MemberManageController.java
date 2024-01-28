package com.nhnacademy.minidooray.gateway.controller.member;

import com.nhnacademy.minidooray.gateway.domain.member.request.MemberProjectRequestDTO;
import com.nhnacademy.minidooray.gateway.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/project/member")
@RequiredArgsConstructor
public class MemberManageController {
  private final MemberService memberService;

  @PostMapping("/add")
  public String postMemberInProject(@ModelAttribute MemberProjectRequestDTO memberProjectRequestDTO, @SessionAttribute(name = "ACCOUNT_ID") String accountId,
      RedirectAttributes redirectAttributes) {
    log.debug("postMemberInProject(): accountId -> {}, projectId -> {}", memberProjectRequestDTO.getAccountId(), memberProjectRequestDTO.getProjectId());
    if(accountId.equals(memberProjectRequestDTO.getAccountId()))  {
      redirectAttributes.addFlashAttribute("error", "자기 자신은 멤버로 추가할 수 없습니다");
      return "redirect:/project/" + memberProjectRequestDTO.getProjectId();
    }
    if(!memberService.createMemberInProject(memberProjectRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "이미 존재하는 멤버이거나, 추가하는 과정에서 오류가 발생하였습니다");
      return "redirect:/project/" + memberProjectRequestDTO.getProjectId();
    }

    redirectAttributes.addFlashAttribute("info", "멤버 추가에 성공하였습니다");
    return "redirect:/project/" + memberProjectRequestDTO.getProjectId();
  }

  @PostMapping("/delete")
  public String deleteMemberInProject(@ModelAttribute MemberProjectRequestDTO memberProjectRequestDTO, RedirectAttributes redirectAttributes) {
    if(!memberService.deleteMemberInProject(memberProjectRequestDTO)) {
      redirectAttributes.addFlashAttribute("error", "없는 멤버이거나, 삭제하는 과정에서 오류가 발생하였습니다");
      return "redirect:/project/" + memberProjectRequestDTO.getProjectId();
    }

    redirectAttributes.addFlashAttribute("info", "멤버 삭제에 성공하였습니다");
    return "redirect:/project/" + memberProjectRequestDTO.getProjectId();
  }
}
