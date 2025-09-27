package com.example.university.web.controller;

import com.example.university.service.GiangDayService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/giangDay")
public class GiangDayController {

    @Autowired
    private GiangDayService giangDayService;

    @PostMapping("/assignGV")
    public ResponseEntity<Map<String, Object>> assignGiangVien(
            @RequestParam Long maLHP,
            @RequestParam(required = false) Integer maGVOld,
            @RequestParam Integer maGV,
            @RequestParam(required = false) Integer soTietPhanCong
    ) {

        giangDayService.assignGiangVien(maLHP, maGVOld, maGV, soTietPhanCong);

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "Gán giảng viên thành công!");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/assignTG")
    public ResponseEntity<Map<String, Object>> assignGVTroGiang(
            @RequestParam Long maHP,
            @RequestParam Integer maGVChinh,
            @RequestParam(required = false) Integer maTGOld,
            @RequestParam Integer maGVTroGiang) {

        Map<String, Object> resp = new HashMap<>();
        try {
            giangDayService.assignTroGiangHP(maHP, maGVChinh, maTGOld, maGVTroGiang);
            resp.put("message", "Gán trợ giảng thành công!");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("message", "Lỗi khi gán trợ giảng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

}
