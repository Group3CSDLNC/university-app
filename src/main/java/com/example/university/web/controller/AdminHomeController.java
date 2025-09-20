package com.example.university.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AdminHomeController {

    @GetMapping("")
    public String dashboard() {
        // Trả về template admin/home.html
        return "dashboard/home";
    }
}
