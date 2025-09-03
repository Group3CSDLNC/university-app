package com.example.university.service;

import com.example.university.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository repo;

}
