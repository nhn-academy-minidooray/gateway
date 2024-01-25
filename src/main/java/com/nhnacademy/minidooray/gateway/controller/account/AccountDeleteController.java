package com.nhnacademy.minidooray.gateway.controller.account;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/delete")
@RequiredArgsConstructor
public class AccountDeleteController {
  private final AccountClientService accountClientService;
  @DeleteMapping
  public String doDelete(@SessionAttribute(name = "USER_INFO") String accountId) {

    if(accountClientService.deleteAccount(new AccountInfoRequestDTO(accountId))) {
      // delete success
      return "redirect:/login";
    }
    // todo when delete failed
    return "redirect:/login";
  }
}
