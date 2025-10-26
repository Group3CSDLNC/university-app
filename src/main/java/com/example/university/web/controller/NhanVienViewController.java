package com.example.university.web.controller;

import com.example.university.dto.NhanVienDTO;
import com.example.university.model.NhanVien;
import com.example.university.service.NhanVienService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienViewController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private SessionService sessionService;

    // Danh sách nhân viên
    @GetMapping("/list")
    public String list(Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }

        List<NhanVien> list = nhanVienService.getAll();
        model.addAttribute("sessionService", sessionService);
        model.addAttribute("nhanVienList", list);
        return "nhanvien/nhanvien_list";
    }

    // Form thêm
    @GetMapping("/add")
    public String addForm(Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/nhanvien_form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute NhanVienDTO nv) {
        nhanVienService.add(nv); // gọi store sp_ThemNhanVien
        return "redirect:/nhan-vien/list";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        model.addAttribute("nhanVien", nhanVienService.getById(id));
        return "nhanvien/nhanvien_form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @ModelAttribute NhanVienDTO nv) {
        nv.setMaNV(id);
        nhanVienService.update(nv); // gọi store sp_SuaNhanVien
        return "redirect:/nhan-vien/list";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        nhanVienService.delete(id); // gọi store sp_XoaNhanVien
        return "redirect:/nhan-vien/list";
    }
}
