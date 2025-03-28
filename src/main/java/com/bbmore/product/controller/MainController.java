package com.bbmore.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "index"; // index.html (example-page.html과 유사한 파일)
    }
}