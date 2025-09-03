package com.example.university.service;

import com.example.university.repository.ChuyenNganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChuyenNganhService {

    @Autowired
    private ChuyenNganhRepository repo;

}
