package com.example.university.web.controller;

import com.example.university.model.TaiKhoan;
import com.example.university.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/api/login")
    @ResponseBody // để trả JSON
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password) {
        // gọi service check
        TaiKhoan tk = taiKhoanService.login(email, password);

        if (tk != null) {
            return ResponseEntity.ok(tk);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
}

