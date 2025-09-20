package com.example.university.web.controller;

import com.example.university.service.BaoCaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/baoCao")
public class BaoCaoController {

    @Autowired
    private BaoCaoService service;

    @GetMapping("/bangDiem/{maSV}/{maCTDT}")
    public List<Map<String,Object>> bangDiem(@PathVariable int maSV, @PathVariable int maCTDT) {
        return service.bangDiem(maSV, maCTDT);
    }

    @GetMapping("/chuaHoanThanh/{maCTDT}")
    public List<Map<String,Object>> chuaHoanThanh(@PathVariable int maCTDT) {
        return service.chuaHoanThanh(maCTDT);
    }

    @GetMapping("/siSo")
    public List<Map<String,Object>> baoCaoSiSo() {
        return service.baoCaoSiSo();
    }
}
