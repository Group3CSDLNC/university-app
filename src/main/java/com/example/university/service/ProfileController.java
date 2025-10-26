package com.example.university.service;

import com.example.university.dto.TaiKhoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private SessionService sessionService;
    @GetMapping("/profile")
    public String getTaiKhoan(Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        int maTK = sessionService.getCurrentUser().getMaTK();
        TaiKhoanDTO taiKhoan = taiKhoanService.getTaiKhoanById(maTK);
        model.addAttribute("taiKhoan", taiKhoan);
        return "profile";
    }
}
