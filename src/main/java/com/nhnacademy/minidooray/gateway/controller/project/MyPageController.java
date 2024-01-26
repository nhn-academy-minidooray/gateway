package com.nhnacademy.minidooray.gateway.controller.project;

import com.nhnacademy.minidooray.gateway.domain.account.Account;
import com.nhnacademy.minidooray.gateway.domain.account.request.AccountInfoRequestDTO;
import com.nhnacademy.minidooray.gateway.domain.account.response.AccountInfoResponseDTO;
import com.nhnacademy.minidooray.gateway.exception.AccountInfoNotFoundException;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/project/mypage")
@RequiredArgsConstructor
public class MyPageController {
  private final AccountClientService accountClientService;

  @GetMapping
  public String getMyPage(@SessionAttribute(name= "ACCOUNT_ID") String accountId, Model model) {
    Optional<AccountInfoResponseDTO> accountWrapper = accountClientService.readAccount(new AccountInfoRequestDTO(accountId));
    if(accountWrapper.isPresent()) {
      AccountInfoResponseDTO account = accountWrapper.get();
      model.addAttribute("ACCOUNT_INFO", account);
      return "project/mypage";
    }
    throw new AccountInfoNotFoundException("계정 정보를 찾을 수 없습니다.");
  }
}
