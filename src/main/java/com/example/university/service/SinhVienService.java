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

    public List<SinhVien> getAll() { return repo.findAll(); }

    public SinhVien getById(int maSV) { return repo.findById(maSV); }

    public void add(SinhVien sv) { repo.add(sv); }

    public void update(SinhVien sv) { repo.update(sv); }

    public void delete(int maSV) { repo.delete(maSV); }
}
