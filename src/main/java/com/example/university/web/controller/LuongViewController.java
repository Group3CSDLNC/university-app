package com.example.university.web.controller;

import com.example.university.model.NhanVien;
import com.example.university.service.NhanVienService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.university.model.NhanVien;


import java.util.*;


@Controller
@RequestMapping("/luong")
public class LuongViewController {

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/list")
    public String xemLuong(
            @RequestParam(required = false) Integer maNV,
            @RequestParam(required = false) Integer thang,
            @RequestParam(required = false) Integer nam,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }

        if (Objects.equals(sessionService.getCurrentUser().getLoaiTaiKhoan(), "NhanVien")) {
            NhanVien nhanVien = nhanVienService.getById(sessionService.getCurrentUser().getMaNV());
            if (nhanVien != null) {
                maNV = nhanVien.getMaNV();
            }
        }

        // Set session cho Thymeleaf
        model.addAttribute("sessionService", sessionService);

        // Nếu tháng/năm không được chọn, lấy tháng/năm hiện tại
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH từ 0-11
        int currentYear = cal.get(Calendar.YEAR);

        int selectedMonth = (thang != null) ? thang : currentMonth;
        int selectedYear = (nam != null) ? nam : currentYear;

        // Lấy danh sách lương
        List<Map<String, Object>> listLuong = nhanVienService.getLuong(maNV, selectedMonth, selectedYear);
        model.addAttribute("listLuong", listLuong);

        // Lấy danh sách nhân viên
        List<NhanVien> nhanViens = nhanVienService.getAll();
        model.addAttribute("nhanViens", nhanViens);

        // Tạo monthMap
        Map<Integer, String> monthMap = new LinkedHashMap<>();
        monthMap.put(1, "Tháng 1");
        monthMap.put(2, "Tháng 2");
        monthMap.put(3, "Tháng 3");
        monthMap.put(4, "Tháng 4");
        monthMap.put(5, "Tháng 5");
        monthMap.put(6, "Tháng 6");
        monthMap.put(7, "Tháng 7");
        monthMap.put(8, "Tháng 8");
        monthMap.put(9, "Tháng 9");
        monthMap.put(10, "Tháng 10");
        monthMap.put(11, "Tháng 11");
        monthMap.put(12, "Tháng 12");
        model.addAttribute("monthMap", monthMap);

        // Tạo yearMap, ví dụ 2023-2025
        Map<Integer, Integer> yearMap = new LinkedHashMap<>();
        yearMap.put(2023, 2023);
        yearMap.put(2024, 2024);
        yearMap.put(2025, 2025);
        yearMap.put(2026, 2026);
        yearMap.put(2027, 2027);
        model.addAttribute("yearMap", yearMap);

        // Selected values
        model.addAttribute("selectedMaNV", maNV);
        model.addAttribute("selectedThang", selectedMonth);
        model.addAttribute("selectedNam", selectedYear);

        return "luong/nhanvien_luong"; // template Thymeleaf
    }
}
