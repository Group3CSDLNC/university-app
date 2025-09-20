package com.example.university.service;

import com.example.university.repository.DangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DangKyService {

    @Autowired
    private DangKyRepository repo;

    public void dangKyHoc(int maSV, String maLHP) {
        repo.dangKyHoc(maSV, maLHP);
    }
}
