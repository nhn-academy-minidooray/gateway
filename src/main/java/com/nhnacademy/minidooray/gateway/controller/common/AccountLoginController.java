package com.nhnacademy.minidooray.gateway.controller.common;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountStatusInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AccountLoginController {
  private final AccountClientService accountClientService;

  @GetMapping
  public String getLoginPage(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if(Objects.nonNull(session) && Objects.nonNull(session.getAttribute("ACCOUNT_ID"))) {
      return "redirect:/project/home";
    }
    return "common/login";
  }

  @PostMapping
  public String doLogin(@Valid @ModelAttribute AccountLoginRequestDTO accountLoginRequestDTO, BindingResult bindingResult
      , HttpServletRequest request, RedirectAttributes redirectAttributes) {
    if(bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("error", "아이디나 비밀번호가 규칙에 맞지 않습니다! 다시 확인해 주세요!");
      return "redirect:/login";
    }

    Optional<AccountStatusInfoResponseDTO> accountInfoWrapped = accountClientService.doLogin(accountLoginRequestDTO);
    if(accountInfoWrapped.isPresent()) {
      AccountStatusInfoResponseDTO accountInfo = accountInfoWrapped.get();
      String accountId = accountInfo.getId();
      String accountStatus = accountInfo.getStatus();
      if("가입".equals(accountStatus)) {
        HttpSession session = request.getSession(true);
        session.setAttribute("ACCOUNT_ID", accountId);
        return "redirect:/project/home";
      } else if("휴면".equals(accountStatus)) {
        redirectAttributes.addFlashAttribute("error", "휴면 상태의 유저는 계정 활성화 작업이 필요합니다");
        return "redirect:/login";
      } else if("탈퇴".equals(accountStatus)) {
        redirectAttributes.addFlashAttribute("error", "탈퇴를 진행한 계정은 로그인할 수 없습니다");
        return "redirect:/login";
      }
    }
    redirectAttributes.addFlashAttribute("error", "잘못된 아이디 혹은 비밀번호를 입력하셨습니다");
    return "redirect:/login";
  }
}
