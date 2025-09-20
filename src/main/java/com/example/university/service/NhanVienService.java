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

    public List<NhanVien> getAll() { return repo.findAll(); }

    public NhanVien getById(int maNV) { return repo.findById(maNV); }

    public void add(NhanVien nv) { repo.add(nv); }

    public void update(NhanVien nv) { repo.update(nv); }

    public void delete(int maNV) { repo.delete(maNV); }
}
