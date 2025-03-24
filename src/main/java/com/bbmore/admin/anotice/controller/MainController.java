package com.bbmore.admin.anotice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping(value = {"/", "/main"})
  public String main() {return "main/main";}
}
