package com.example.university.web.controller;

import com.example.university.service.DangKyService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<?> dangKy(@RequestBody Map<String, Object> req) {
        Long maSV = sessionService.getCurrentUser().getMaSV();
        String maLHP = (String) req.get("maLHP");

        service.dangKyHoc(maSV, maLHP);
        return ResponseEntity.ok(Collections.singletonMap("message", "Đăng ký thành công"));
    }

    @PostMapping("/huy")
    public ResponseEntity<?> huyDangKy(@RequestBody Map<String, Object> req) {
        Long maSV = sessionService.getCurrentUser().getMaSV();
        String maLHP = (String) req.get("maLHP");

        boolean success = service.huyDangKyHoc(maSV, maLHP);

        if (success) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Hủy đăng ký thành công"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Không thể hủy đăng ký lớp học phần này"));
        }
    }
}
