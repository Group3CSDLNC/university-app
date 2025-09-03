package com.example.university.service;

import com.example.university.repository.KetQuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KetQuaService {

    @Autowired
    private KetQuaRepository repo;

}
