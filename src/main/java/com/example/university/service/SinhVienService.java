package com.example.university.service;

import com.example.university.model.SinhVien;
import com.example.university.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository repo;

    public List<SinhVien> getAllSinhVien() {
        return repo.findAll();
    }

    public SinhVien getSinhVienById(int id) {
        return repo.findById(id);
    }

    public void addSinhVien(SinhVien sv) {
        repo.save(sv);
    }

    public void updateSinhVien(SinhVien sv) {
        repo.update(sv);
    }

    public void deleteSinhVien(int id) {
        repo.delete(id);
    }
}
