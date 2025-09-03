package com.example.university.web.controller;

import com.example.university.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taiKhoan")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

}
