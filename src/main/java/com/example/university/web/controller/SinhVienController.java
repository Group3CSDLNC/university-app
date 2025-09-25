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
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SinhVien getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SinhVien sv) {
        service.add(sv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Thêm sinh viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SinhVien sv) {
        sv.setMaSV(id);
        service.update(sv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật sinh viên thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa sinh viên thành công"));
    }
}
