package com.nhnacademy.minidooray.gateway.controller.common;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountLoginRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AccountLoginController {
  private final AccountClientService accountClientService;

  @GetMapping
  public String getLoginPage() {
    return "common/login";
  }

  @PostMapping
  public String doLogin(@Valid @ModelAttribute AccountLoginRequestDTO accountLoginRequestDTO, BindingResult bindingResult, HttpServletRequest request) {
    if(bindingResult.hasErrors())
      throw new ValidationException();
    // todo exception handling

    if(accountClientService.doLogin(accountLoginRequestDTO)) {
      // todo account exist ? password 비교를 서버쪽에서 하는게 좋을거같다.
      String accountId = accountLoginRequestDTO.getId();
      HttpSession session = request.getSession(true);
      session.setAttribute("ACCOUNT_ID", accountId);
      log.debug("doLogin(): login success, id -> ", session.getAttribute("ACCOUNT_ID"));
      return "project/home";
    }

    log.debug("doLogin(): login failed");
    // todo do something when login failed
    return "redirect:/login";
  }
}
