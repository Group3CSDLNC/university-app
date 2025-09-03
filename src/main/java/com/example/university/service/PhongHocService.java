package com.example.university.service;

import com.example.university.repository.PhongHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhongHocService {

    @Autowired
    private PhongHocRepository repo;

}
