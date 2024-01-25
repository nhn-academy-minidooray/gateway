package com.nhnacademy.minidooray.gateway.controller.common;

import com.nhnacademy.minidooray.gateway.domain.account.request.AccountRegisterRequestDTO;
import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class AccountRegisterController {
  private AccountClientService accountClientService;

  @GetMapping
  public String getRegisterPage() {
    return "common/register";
  }

  @PostMapping
  public String doRegister(@Valid AccountRegisterRequestDTO accountRegisterRequestDTO, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
      throw new ValidationException();
    //todo exception handling

    if(accountClientService.createAccount(accountRegisterRequestDTO))
      return "redirect:/login";

    //todo do something when register failed
    return "redirect:/login";
  }

}
