package com.example.university.service;

import com.example.university.model.Khoa;
import com.example.university.repository.KhoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhoaService {

    @Autowired
    private KhoaRepository repo;

    public List<Khoa> getAll() {
        return repo.findAll();
    }

}
