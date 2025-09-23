package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DangKyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dangKyHoc(int maSV, String maLHP) {
        jdbcTemplate.update("EXEC sp_DangKyHoc ?, ?", maSV, maLHP);
    }

    public void huyDangKyHoc(int maSV, String maLHP) {
        jdbcTemplate.update("EXEC sp_HuyDangKyHoc ?, ?", maSV, maLHP);
    }
}
