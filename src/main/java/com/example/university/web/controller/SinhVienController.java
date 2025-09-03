package com.example.university.web.controller;

import com.example.university.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/sinhVien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;

}
