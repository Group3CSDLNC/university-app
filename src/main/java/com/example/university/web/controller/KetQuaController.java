package com.example.university.web.controller;

import com.example.university.service.KetQuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/taiKhoan")
public class KetQuaController {

    @Autowired
    private KetQuaService ketQuaService;
    @GetMapping("/xem")
    public ResponseEntity<List<Map<String, Object>>> xemKetQuaHocTap(
            @RequestParam Long maSV,
            @RequestParam(required = false) Long maHP,
            @RequestParam(required = false) Integer maCN) {
        return ResponseEntity.ok(ketQuaService.xemKetQuaHocTap(maSV, maHP, maCN));
    }

    @GetMapping("/tongket")
    public ResponseEntity<List<Map<String, Object>>> tinhKetQuaHocTapTongKet(
            @RequestParam(required = false) Long maSV,
            @RequestParam(required = false) Integer maCN) {
        return ResponseEntity.ok(ketQuaService.tinhKetQuaHocTapTongKetV3(maSV, maCN));
    }
}
