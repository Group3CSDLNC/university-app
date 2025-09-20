package com.example.university.web.controller;

import com.example.university.service.DangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/dangKy")
public class DangKyController {

    @Autowired
    private DangKyService service;

    @PostMapping
    public ResponseEntity<?> dangKy(@RequestBody Map<String, Object> req) {
        int maSV = (int) req.get("maSV");
        String maLHP = (String) req.get("maLHP");

        service.dangKyHoc(maSV, maLHP);
        return ResponseEntity.ok(Collections.singletonMap("message", "Đăng ký thành công"));
    }
}
