package com.example.university.web.controller;

import com.example.university.service.LopHocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lopHocPhan")
public class LopHocPhanController {

    @Autowired
    private LopHocPhanService lopHocPhanService;

}
