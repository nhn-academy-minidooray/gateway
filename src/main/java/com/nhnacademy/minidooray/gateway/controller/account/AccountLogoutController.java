package com.nhnacademy.minidooray.gateway.controller.account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
@RequiredArgsConstructor
public class AccountLogoutController {

  @GetMapping
  public String doLogout(HttpSession session, HttpServletResponse response) {
    Cookie cookie = new Cookie("JSESSIONID", null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    session.invalidate();

    return "redirect:/login";
  }

}
