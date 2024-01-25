package com.nhnacademy.minidooray.gateway.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/home")
public class HomePageController {
  @GetMapping
  public String getHomePage() {
    return "project/home";
  }
}
