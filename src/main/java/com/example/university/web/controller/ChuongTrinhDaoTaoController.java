package com.example.university.web.controller;

import com.example.university.model.*;
import com.example.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/ctdt")
public class ChuongTrinhDaoTaoController {

    @Autowired
    private ChuongTrinhDaoTaoService ctdtService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ChuyenNganhService chuyenNganhService;
    @Autowired
    private KhoaService khoaService;
    @Autowired
    private NhanVienService nhanVienService;

    // Kiểm tra quyền hạn
    private boolean hasPermission(String... allowedRoles) {
        try {
            SessionUser user = sessionService.getCurrentUser();
            if (user == null) {
                return false;
            }
            String role = user.getLoaiTaiKhoan();
            for (String allowedRole : allowedRoles) {
                if (allowedRole.equalsIgnoreCase(role)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false; // Trả về false nếu có lỗi (ví dụ: chưa đăng nhập)
        }
    }

    @GetMapping("/list")
    public String listCTDT(@RequestParam(required = false) Integer maKhoa,
                           @RequestParam(required = false) String namHoc,
                           @RequestParam(required = false) Integer maNVQuanLy,
                           Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";// quay lại trang login
        }
        try {
            List<String> years = new ArrayList<>();
            for (int start = 2020; start <= 2028; start++) {
                years.add(start + "-" + (start + 1));
            }

            List<ChuyenNganh> cns = chuyenNganhService.getAll();
            List<Khoa> khoas = khoaService.getAll();
            List<NhanVien> nhanViens = nhanVienService.findByVaiTro(Collections.singletonList("Quan ly"));


            Map<Long, String> cnMap = cns.stream()
                    .collect(Collectors.toMap(ChuyenNganh::getMaCN, ChuyenNganh::getTenCN));

            List<ChuongTrinhDaoTao> ctdtList = ctdtService.listCTDT(maKhoa, namHoc, maNVQuanLy);

            model.addAttribute("ctdtList", ctdtList);
            model.addAttribute("maKhoa", maKhoa);
            model.addAttribute("namHoc", namHoc);
            model.addAttribute("maNVQuanLy", maNVQuanLy);
            model.addAttribute("years", years);
            model.addAttribute("cns", cns);
            model.addAttribute("khoas", khoas);
            model.addAttribute("nhanViens", nhanViens);
            model.addAttribute("cnMap", cnMap);
            model.addAttribute("sessionService", sessionService);
            return "ctdt/list";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}

