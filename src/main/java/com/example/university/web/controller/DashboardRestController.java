package com.example.university.web.controller;

import com.example.university.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/count")
public class DashboardRestController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> getSummary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    @GetMapping("/giangvien/{maGV}")
    public ResponseEntity<Map<String, Object>> getGiangVienSummary(@PathVariable int maGV) {
        return ResponseEntity.ok(dashboardService.getGiangVienSummary(maGV));
    }
    @GetMapping("/sinhvien/{maSV}")
    public ResponseEntity<Map<String, Object>> getSinhVienDashboard(@PathVariable int maSV) {
        return ResponseEntity.ok(dashboardService.getSinhVienDashboard(maSV));
    }

}


