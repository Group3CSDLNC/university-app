package com.example.university.web.controller;

import com.example.university.service.PhongHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/phongHoc")
public class PhongHocController {
    @Autowired
    private PhongHocService phongHocService;

}
