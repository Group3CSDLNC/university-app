package com.example.university.service;

import com.example.university.repository.LichHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LichHocService {

    @Autowired
    private LichHocRepository repo;

}
