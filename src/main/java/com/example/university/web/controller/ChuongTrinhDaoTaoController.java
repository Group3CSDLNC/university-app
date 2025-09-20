package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.SessionService;
import com.example.university.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/chuongTrinhDaoTao")
public class ChuongTrinhDaoTaoController {

    @Autowired
    private ChuongTrinhDaoTaoService service;

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        // Kiểm tra login
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }

        List<ChuongTrinhDaoTao> ctdts = (keyword == null || keyword.isEmpty())
                ? service.getAll("")
                : service.search(keyword);
        model.addAttribute("ctdts", ctdts);
        return "chuongtrinhdaotao/chuongtrinhdaotao_table";
    }
}

