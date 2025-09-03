package com.example.university.service;

import com.example.university.repository.LichThiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LichThiService {

    @Autowired
    private LichThiRepository repo;
}
