package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DangKyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

}
