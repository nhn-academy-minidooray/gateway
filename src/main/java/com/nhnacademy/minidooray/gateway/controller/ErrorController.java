package com.nhnacademy.minidooray.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorController {
  @ExceptionHandler(Exception.class)
  public String handleException(Exception e, Model model) {
    model.addAttribute("errorInfo", "An error occurred.");
    model.addAttribute("exceptionDetails", e.getMessage());
    model.addAttribute("debug", true);
    return "error";
  }
}
