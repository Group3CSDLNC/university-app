package com.example.university.service;

import com.example.university.dto.TaiKhoanDTO;
import com.example.university.model.TaiKhoan;
import com.example.university.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository repo;

    public TaiKhoan login(String tenDangNhap, String matKhau) {
        return repo.checkLogin(tenDangNhap, matKhau);
    }

    public TaiKhoanDTO getTaiKhoanById(int maTK) {
        return repo.getTaiKhoanById(maTK);
    }
}
