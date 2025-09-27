package com.example.university.web.controller;

import com.example.university.dto.LopHocPhanDTO;
import com.example.university.service.LopHocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/lopHocPhan")
public class LopHocPhanApiController {

    @Autowired
    private LopHocPhanService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam Long maHP,
                                 @RequestParam int hocKy,
                                 @RequestParam String namHoc,
                                 @RequestParam Long maCTDT,
                                 @RequestParam int siSo) {
        Long maLHP = service.add(maHP, hocKy, namHoc, maCTDT, siSo);
        return ResponseEntity.ok(Collections.singletonMap("maLHP", maLHP));
    }

    @PutMapping("/update/{maLHP}")
    public ResponseEntity<?> update(@PathVariable Long maLHP,
                                    @RequestParam Long maHP,
                                    @RequestParam int hocKy,
                                    @RequestParam String namHoc,
                                    @RequestParam Long maCTDT,
                                    @RequestParam int siSo) {
        service.update(maLHP, maHP, maCTDT, hocKy, namHoc, siSo);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{maLHP}")
    public ResponseEntity<?> delete(@PathVariable Long maLHP) {
        service.delete(maLHP);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa thành công"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(required = false) Integer hocKy,
                                  @RequestParam(required = false) String namHoc,
                                  @RequestParam(required = false) Long maCTDT,
                                  @RequestParam(required = false) Integer maGV) {
        if (Objects.equals(namHoc, "")) namHoc = null;
        return ResponseEntity.ok(service.list(hocKy, namHoc, maCTDT, maGV));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> search(
            @RequestParam(required = false) Integer hocKy,
            @RequestParam(required = false) String namHoc,
            @RequestParam(required = false) Long maCTDT,
            @RequestParam(required = false) Integer maGV,
            @RequestParam(required = false) Long maHP) {

        List<Map<String, Object>> danhSach = service.getLopHocPhan(hocKy, namHoc, maCTDT, maGV, maHP);
        return ResponseEntity.ok(danhSach);
    }

    @GetMapping("/available")
    public ResponseEntity<List<LopHocPhanDTO>> getAvailableLopHocPhan(
            @RequestParam Long maSV,
            @RequestParam String namHoc,
            @RequestParam int hocKy,
            @RequestParam(required = false) Long maCN) {
        List<LopHocPhanDTO> list = service.getAvailableLopHocPhan(maSV, namHoc, hocKy, maCN);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignGiangVien(
            @RequestParam Long maLHP,
            @RequestParam Long maHP,
            @RequestParam Integer maGVChinh,
            @RequestParam(required = false) Integer soTietPhanCong,
            @RequestParam(required = false) Integer maGVTroGiang) {

        service.assignGiangVien(maLHP, maHP, maGVChinh, soTietPhanCong, maGVTroGiang);
        return ResponseEntity.ok(Collections.singletonMap("message", "Gán giảng viên thành công"));
    }
}
