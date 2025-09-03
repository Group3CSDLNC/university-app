package com.example.university.service;

import com.example.university.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository repo;

}
