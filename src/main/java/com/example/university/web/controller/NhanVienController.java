package com.example.university.web.controller;

import com.example.university.model.NhanVien;
import com.example.university.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhanVien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    // Lấy tất cả nhân viên
    @GetMapping
    public List<NhanVien> getAll() {
        return nhanVienService.getAllNhanVien();
    }

    // Lấy nhân viên theo id
    @GetMapping("/{id}")
    public NhanVien getById(@PathVariable int id) {
        return nhanVienService.getNhanVienById(id);
    }

    // Thêm nhân viên
    @PostMapping
    public void create(@RequestBody NhanVien nv) {
        nhanVienService.addNhanVien(nv);
    }

    // Cập nhật nhân viên
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody NhanVien nv) {
        nv.setMaNV(id);
        nhanVienService.updateNhanVien(nv);
    }

    // Xóa nhân viên
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);
    }


}
