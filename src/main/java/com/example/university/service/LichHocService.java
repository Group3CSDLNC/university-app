package com.example.university.service;

import com.example.university.model.LichHoc;
import com.example.university.repository.LichHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichHocService {

    @Autowired
    private LichHocRepository repo;

    public List<LichHoc> getAll() { return repo.findAll(); }

    public void add(LichHoc lh) { repo.add(lh); }

    public void update(LichHoc lh) { repo.update(lh); }

    public void delete(int id) { repo.delete(id); }

}
