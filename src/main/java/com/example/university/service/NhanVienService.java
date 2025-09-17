package com.example.university.service;

import com.example.university.model.NhanVien;
import com.example.university.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository repo;

    public List<NhanVien> getAllNhanVien() {
        return repo.findAll();
    }

    public void addNhanVien(NhanVien nv) {
        repo.save(nv);
    }

    public void updateNhanVien(NhanVien nv) {
        repo.update(nv);
    }

    public void deleteNhanVien(int maNV) {
        repo.delete(maNV);
    }

    public NhanVien getNhanVienById(int maNV) {
        return repo.findById(maNV);
    }
}
