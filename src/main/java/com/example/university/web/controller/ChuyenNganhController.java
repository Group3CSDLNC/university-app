package com.example.university.web.controller;

import com.example.university.service.ChuyenNganhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chuyenNganh")
public class ChuyenNganhController {

    @Autowired
    private ChuyenNganhService chuyenNganhService;

}
