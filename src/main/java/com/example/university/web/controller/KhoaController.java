package com.example.university.web.controller;

import com.example.university.model.Khoa;
import com.example.university.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/khoa")
public class KhoaController {

    @Autowired
    KhoaService khoaService;

    @GetMapping("/getAll")
    public List<Khoa> getAll() {
        return khoaService.getAll();
    }
}
