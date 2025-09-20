package com.example.university.web.controller;

import com.example.university.model.LichHoc;
import com.example.university.service.LichHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/lichHoc")
public class LichHocController {

    @Autowired
    private LichHocService service;

    @GetMapping
    public List<LichHoc> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody LichHoc lh) {
        service.add(lh);
        return ResponseEntity.ok(Collections.singletonMap("message", "Thêm lịch học thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody LichHoc lh) {
        lh.setMaLichHoc(id);
        service.update(lh);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật lịch học thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa lịch học thành công"));
    }
}
