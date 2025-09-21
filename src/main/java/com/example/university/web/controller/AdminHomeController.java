package com.example.university.web.controller;

import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AdminHomeController {
    @Autowired
    private SessionService sessionService;
    @GetMapping("")
    public String dashboard() {
        // Trang dashboard mặc định, có thể redirect dựa trên role
        String role = sessionService.getCurrentUser().getLoaiTaiKhoan();
        switch (role) {
            case "Admin":
                return "redirect:/dashboard/admin";
            case "SinhVien":
                return "redirect:/dashboard/sinhvien";
            case "NhanVien":
                return "redirect:/dashboard/nhanvien";
            case "QuanLy":
                return "redirect:/dashboard/quanly";
            default:
                return "dashboard/home";
        }
    }
    @GetMapping("/admin")
    public String dashboardAdmin() {
        return "dashboard/admin";
    }

    @GetMapping("/sinhvien")
    public String dashboardSinhVien() {
        return "dashboard/sinhvien";
    }

    @GetMapping("/nhanvien")
    public String dashboardNhanVien() {
        return "dashboard/nhanvien";
    }

    @GetMapping("/quanly")
    public String dashboardQuanLy() {
        return "dashboard/quanly";
    }
}
