package com.example.university.web.controller;

import com.example.university.service.KetQuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ketQua")
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
    // Thêm điểm
    @PostMapping("/them")
    public ResponseEntity<?> themDiem(@RequestBody Map<String, Object> req) {
        try {
            Long maSV = Long.valueOf(req.get("maSV").toString());
            Long maHP = Long.valueOf(req.get("maHP").toString());
            Double diemCC = req.get("diemCC") != null ? Double.valueOf(req.get("diemCC").toString()) : null;
            Double diemGK = req.get("diemGK") != null ? Double.valueOf(req.get("diemGK").toString()) : null;
            Double diemCK = req.get("diemCK") != null ? Double.valueOf(req.get("diemCK").toString()) : null;

            ketQuaService.themDiem(maSV, maHP, diemCC, diemGK, diemCK);

            Map<String, String> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Thêm điểm thành công");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            Map<String, String> resp = new HashMap<>();
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    // Sửa điểm
    @PostMapping("/sua")
    public ResponseEntity<?> suaDiem(@RequestBody Map<String, Object> req) {
        try {
            Long maSV = Long.valueOf(req.get("maSV").toString());
            Long maHP = Long.valueOf(req.get("maHP").toString());
            Integer lanThi = Integer.valueOf(req.get("lanThi").toString());
            Double diemCC = req.get("diemCC") != null ? Double.valueOf(req.get("diemCC").toString()) : null;
            Double diemGK = req.get("diemGK") != null ? Double.valueOf(req.get("diemGK").toString()) : null;
            Double diemCK = req.get("diemCK") != null ? Double.valueOf(req.get("diemCK").toString()) : null;

            ketQuaService.suaDiem(maSV, maHP, lanThi, diemCC, diemGK, diemCK);

            Map<String, String> resp = new HashMap<>();
            resp.put("status", "success");
            resp.put("message", "Sửa điểm thành công");
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            Map<String, String> resp = new HashMap<>();
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
