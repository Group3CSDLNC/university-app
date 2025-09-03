package com.example.university.web.controller;

import com.example.university.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hocPhan")
public class HocPhanController {

    @Autowired
    private HocPhanService hocPhanService;

}
