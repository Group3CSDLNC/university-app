package com.example.university.web.controller;

import com.example.university.model.HocPhan;
import com.example.university.model.Khoa;
import com.example.university.service.KhoaService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class KhoaController {

    @Autowired
    private KhoaService khoaService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/khoa/list")
    public String listKhoa(Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }

        List<Khoa> khoaList = khoaService.getAll();
        model.addAttribute("khoaList", khoaList);
        model.addAttribute("sessionService", sessionService);

        return "khoa/list"; // view thymeleaf
    }
}
