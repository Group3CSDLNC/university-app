package com.example.university.web.controller;

import com.example.university.service.DangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dangKy")
public class DangKyController {

    @Autowired
    private DangKyService dangKyService;

}
