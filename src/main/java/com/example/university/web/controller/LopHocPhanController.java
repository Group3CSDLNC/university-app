package com.example.university.web.controller;

import com.example.university.service.LopHocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/lopHocPhan")
public class LopHocPhanController {

    @Autowired
    private LopHocPhanService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam int maHP,
                                 @RequestParam int hocKy,
                                 @RequestParam String namHoc,
                                 @RequestParam int maCTDT,
                                 @RequestParam int siSo) {
        String maLHP = service.add(maHP, hocKy, namHoc, maCTDT, siSo);
        return ResponseEntity.ok(Collections.singletonMap("maLHP", maLHP));
    }

    @PutMapping("/update/{maLHP}")
    public ResponseEntity<?> update(@PathVariable String maLHP,
                                    @RequestParam int hocKy,
                                    @RequestParam String namHoc,
                                    @RequestParam int siSo) {
        service.update(maLHP, hocKy, namHoc, siSo);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{maLHP}")
    public ResponseEntity<?> delete(@PathVariable String maLHP) {
        service.delete(maLHP);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa thành công"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(required = false) Integer hocKy,
                                  @RequestParam(required = false) String namHoc,
                                  @RequestParam(required = false) Integer maCTDT,
                                  @RequestParam(required = false) Integer maGV) {
        return ResponseEntity.ok(service.list(hocKy, namHoc, maCTDT, maGV));
    }
}
