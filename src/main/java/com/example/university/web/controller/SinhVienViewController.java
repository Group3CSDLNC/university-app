package com.example.university.web.controller;

import com.example.university.dto.SinhVienDTO;
import com.example.university.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class SinhVienViewController {

    @Autowired
    private SinhVienService service;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Hiển thị danh sách sinh viên
     */
    @GetMapping("/sinh-vien/list")
    public String listSinhVien(Model model) {
        // Lấy toàn bộ sinh viên
        List<SinhVienDTO> sinhVienList = service.findAll();

        // Lấy danh sách chuyên ngành (dùng cho form select)
        List<Map<String, Object>> chuyenNganhList = jdbcTemplate.queryForList(
                "SELECT MaCN, TenCN FROM ChuyenNganh");

        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("chuyenNganhList", chuyenNganhList);

        return "sinhvien/sinhvien_list"; // Tương ứng với file templates/sinhvien_list.html
    }
}
