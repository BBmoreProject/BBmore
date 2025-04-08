package com.bbmore.admin.amember.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminMainController {

    @GetMapping(value = {"/", "/main"})
    public String main() {
        return "members/list";
    }

}
