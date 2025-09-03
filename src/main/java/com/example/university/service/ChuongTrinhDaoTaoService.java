package com.example.university.service;

import com.example.university.repository.ChuongTrinhDaoTaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChuongTrinhDaoTaoService {

    @Autowired
    private ChuongTrinhDaoTaoRepository repo;

}
