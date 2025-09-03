package com.example.university.web.controller;

import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chuongTrinhDaoTao")
public class ChuongTrinhDaoTaoController {

    @Autowired
    private ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;

}
