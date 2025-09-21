package com.example.university.web.controller;

import com.example.university.model.Khoa;
import com.example.university.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/khoa")
public class KhoaApiController {

    @Autowired
    KhoaService khoaService;

    @GetMapping("/all")
    public List<Khoa> getAll() {
        return khoaService.getAll();
    }

    @GetMapping("/{id}")
    public Khoa getById(@PathVariable int id) {
        return khoaService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> add(@RequestBody Khoa khoa) {
        khoaService.addKhoa(khoa);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm khoa thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> update(@RequestBody Khoa khoa) {
        khoaService.updateKhoa(khoa);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cập nhật khoa thành công");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        khoaService.deleteKhoa(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Xóa khoa thành công");
        return ResponseEntity.ok(response);
    }
}
