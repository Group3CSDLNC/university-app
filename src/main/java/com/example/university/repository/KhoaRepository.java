package com.example.university.repository;

import com.example.university.model.Khoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class KhoaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Khoa> findAll() {
        String sql = "EXEC sp_GetAllKhoa";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Khoa k = new Khoa();
            k.setMaKhoa(rs.getInt("MaKhoa"));
            k.setTenKhoa(rs.getString("TenKhoa"));
            k.setEmail(rs.getString("Email"));
            return k;
        });
    }

}
