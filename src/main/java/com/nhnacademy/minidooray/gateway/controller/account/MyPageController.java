package com.nhnacademy.minidooray.gateway.controller.account;

import com.nhnacademy.minidooray.gateway.domain.account.Account;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
  private final AccountClientService accountClientService;

  @GetMapping
  public String getMyPage(@SessionAttribute(name= "ACCOUNT_ID") String accountId, Model model) {
    AccountInfoResponseDTO account = accountClientService.readAccount(new AccountInfoRequestDTO(accountId));
    model.addAttribute("ACCOUNT_INFO", account);
    return "account/mypage";
  }
}
