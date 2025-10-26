package com.example.university.web.controller;

import com.example.university.dto.NhanVienDTO;
import com.example.university.model.NhanVien;
import com.example.university.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/nhanVien")
public class NhanVienController {

    @Autowired
    private NhanVienService service;

    @GetMapping
    public List<NhanVien> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NhanVienDTO getNhanVien(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/giangvienList")
    public List<NhanVien> getGiangVien() {
        return service.findByVaiTro(Collections.singletonList("Giang vien"));
    }
    @GetMapping("/trogiangList")
    public List<NhanVien> getTroGiang() {
        return service.findByVaiTro(Collections.singletonList("Tro giang"));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NhanVienDTO nv) {
        service.add(nv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Thêm nhân viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody NhanVienDTO nv) {
        nv.setMaNV(id);
        service.update(nv);
        return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật nhân viên thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Xóa nhân viên thành công"));
    }

    @GetMapping("/luong/{thang}/{nam}")
    public ResponseEntity<?> danhSachLuongGV(@PathVariable int thang,
                                             @PathVariable int nam) {
        return ResponseEntity.ok(service.danhSachLuongGV(thang, nam));
    }

    // Giảng viên chính
    @GetMapping("/giangVienChinh/by-lhp/{maLHP}")
    public List<NhanVien> getGiangVienChinhByLHP(@PathVariable Long maLHP) {
        return service.getGiangVienChinhByLHP(maLHP);
    }

    // Trợ giảng
    @GetMapping("/troGiang/by-lhp/{maLHP}")
    public List<NhanVien> getTroGiangByLHP(@PathVariable Long maLHP) {
        return service.getTroGiangByLHP(maLHP);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable int id) {
        service.delete(id);
        return "Xóa thành công";
    }
}