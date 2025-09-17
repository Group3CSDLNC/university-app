package com.example.university.web.controller;

import com.example.university.model.SinhVien;
import com.example.university.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/sinhVien")
public class SinhVienController {

    @Autowired
    private SinhVienService service;

    @GetMapping
    public List<SinhVien> getAll() {
        return service.getAllSinhVien();
    }

    @GetMapping("/{id}")
    public SinhVien getById(@PathVariable int id) {
        return service.getSinhVienById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SinhVien sv) {
        service.addSinhVien(sv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Thêm học viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody SinhVien sv) {
        sv.setMaSV(id);
        service.updateSinhVien(sv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật học viên thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.deleteSinhVien(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa học viên thành công"));
    }
}
