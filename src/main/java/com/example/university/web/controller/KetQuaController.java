package com.example.university.web.controller;

import com.example.university.service.KetQuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taiKhoan")
public class KetQuaController {

    @Autowired
    private KetQuaService ketQuaService;

}
