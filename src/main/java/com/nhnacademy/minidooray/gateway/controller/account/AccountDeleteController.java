package com.nhnacademy.minidooray.gateway.controller.account;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping("/delete")
@RequiredArgsConstructor
public class AccountDeleteController {
  private final AccountClientService accountClientService;
  @DeleteMapping
  public String doDelete(@SessionAttribute(name = "USER_INFO") String accountId) {
    //todo 탈퇴 진행
    if(accountClientService.deleteAccount(new AccountInfoRequestDTO(accountId))) {
      log.debug("doDelete(): delete success");
      return "redirect:/login";
    }
    log.debug("doDelete(): delete failed");
    return "redirect:/login";
  }
}
