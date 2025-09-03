package com.example.university.service;

import com.example.university.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository repo;

}
