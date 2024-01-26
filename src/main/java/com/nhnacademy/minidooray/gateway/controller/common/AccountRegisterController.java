package com.nhnacademy.minidooray.gateway.controller.common;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import com.nhnacademy.minidooray.gateway.service.account.impl.AccountClientServiceImpl;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class AccountRegisterController {
  private final AccountClientService accountClientService;

  @GetMapping
  public String getRegisterPage() {
    return "common/register";
  }

  @PostMapping
  public String doRegister(@Valid AccountRegisterRequestDTO accountRegisterRequestDTO, BindingResult bindingResult
      , RedirectAttributes redirectAttributes) {
    if(bindingResult.hasErrors()) {
      log.debug("doRegister(): {}", bindingResult.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()
          ).collect(
          Collectors.joining(", ")));
      redirectAttributes.addFlashAttribute("error", "생성 규칙에 맞게 다시 입력해 주세요");
      return "redirect:/register";
    }

    log.debug("doRegister(): id -> {}, pw -> {}, name -> {}, email -> {}", accountRegisterRequestDTO.getId()
    ,accountRegisterRequestDTO.getPassword(), accountRegisterRequestDTO.getName(), accountRegisterRequestDTO.getEmail());

    if(accountClientService.createAccount(accountRegisterRequestDTO)) {
      log.debug("doRegister(): success create");
      return "redirect:/login";
    }

    //todo do something when register failed
    redirectAttributes.addFlashAttribute("error", "이미 존재하는 아이디이거나, 오류가 발생하였습니다");
    return "redirect:/register";
  }

}
