package com.example.university.web.controller;

import com.example.university.model.NhanVien;
import com.example.university.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/nhanVien")
public class NhanVienController {

    @Autowired
    private NhanVienService service;

    @GetMapping
    public List<NhanVien> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NhanVien getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NhanVien nv) {
        service.add(nv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Thêm nhân viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody NhanVien nv) {
        nv.setMaNV(id);
        service.update(nv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật nhân viên thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa nhân viên thành công"));
    }
}