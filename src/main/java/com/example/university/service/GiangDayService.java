package com.example.university.service;

import com.example.university.repository.GiangDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiangDayService {

    @Autowired
    private GiangDayRepository repo;

}
