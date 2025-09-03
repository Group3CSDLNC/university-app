package com.example.university.web.controller;

import com.example.university.service.LichThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lichThi")
public class LichThiController {

    @Autowired
    private LichThiService lichThiService;

}
