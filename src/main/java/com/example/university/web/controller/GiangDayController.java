package com.example.university.web.controller;

import com.example.university.service.GiangDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/giangDay")
public class GiangDayController {

    @Autowired
    private GiangDayService giangDayService;

}
