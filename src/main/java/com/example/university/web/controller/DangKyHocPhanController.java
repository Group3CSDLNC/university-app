package com.example.university.web.controller;

import com.example.university.dto.LopHocPhanDTO;
import com.example.university.model.SinhVien;
import com.example.university.service.LopHocPhanService;
import com.example.university.service.SessionService;
import com.example.university.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dangKyHocPhan")
public class DangKyHocPhanController {
    @Autowired
    private LopHocPhanService lopHocPhanService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/list")
    public String getAvailableLopHocPhan(
            @RequestParam(value = "namHoc", required = false) String namHoc,
            @RequestParam(value = "hocKy", required = false) Integer hocKy,
            @RequestParam(value = "maCN", required = false) Long maCN,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }

        // Lấy sinh viên hiện tại từ session
        Long maSV = sessionService.getCurrentUser().getMaSV();
        SinhVien sv = sinhVienService.getById(maSV);

        // =======================
        // Gán giá trị mặc định
        // =======================
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        // Tính học kỳ = quý (1 năm có 4 kỳ)
        if (hocKy == null) {
            if (month >= 5 && month <= 6) {
                hocKy = 3;
            } else if (month >= 7 && month <= 8) {
                hocKy = 4;
            } else if (month >= 9 && month <= 12) {
                hocKy = 1;
            } else {
                hocKy = 2;
            }
        }

        // Tính năm học
        if (namHoc == null) {
            if (hocKy == 1 || hocKy == 2) {
                namHoc = year + "-" + (year + 1);
            } else {
                namHoc = (year - 1) + "-" + year;
            }
        }

        // Lấy chuyên ngành theo sinh viên
        if (maCN == null && sv != null) {
            maCN = sv.getMaCN();
        }

        // Gọi service lấy danh sách lớp học phần
        List<LopHocPhanDTO> lopHocPhans =
                lopHocPhanService.getAvailableLopHocPhan(maSV, namHoc, hocKy, maCN);

        // Gắn vào model để Thymeleaf render
        model.addAttribute("lopHocPhans", lopHocPhans);
        model.addAttribute("maSV", maSV);
        model.addAttribute("namHoc", namHoc);
        model.addAttribute("hocKy", hocKy);
        model.addAttribute("maCN", maCN);
        model.addAttribute("hocKy", hocKy);
        model.addAttribute("namHoc", namHoc);

        return "dangkyhocphan";
    }
}
