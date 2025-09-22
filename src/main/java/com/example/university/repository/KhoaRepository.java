package com.example.university.repository;

import com.example.university.model.Khoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class KhoaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addKhoa(Khoa khoa) {
        jdbcTemplate.update("EXEC sp_AddKhoa ?, ?", khoa.getTenKhoa(), khoa.getEmail());
    }
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
    public void updateKhoa(Khoa khoa) {
        jdbcTemplate.update("EXEC sp_UpdateKhoa ?, ?, ?", khoa.getMaKhoa(), khoa.getTenKhoa(), khoa.getEmail());
    }

    public void deleteKhoa(int maKhoa) {
        jdbcTemplate.update("EXEC sp_DeleteKhoa ?", maKhoa);
    }

    public List<Khoa> getAll() {
        return jdbcTemplate.query("EXEC sp_GetAllKhoa",
                new BeanPropertyRowMapper<>(Khoa.class));
    }

    public Khoa getById(int maKhoa) {
        return jdbcTemplate.queryForObject("EXEC sp_GetKhoaById ?",
                new BeanPropertyRowMapper<>(Khoa.class),
                maKhoa);
    }

    public List<Khoa> searchKhoa(String keyword) {
        return jdbcTemplate.query(
                "EXEC sp_SearchKhoa ?",
                new Object[]{keyword},
                (rs, rowNum) -> {
                    Khoa khoa = new Khoa();
                    khoa.setMaKhoa(rs.getInt("MaKhoa"));
                    khoa.setTenKhoa(rs.getString("TenKhoa"));
                    khoa.setEmail(rs.getString("Email"));
                    return khoa;
                }
        );
    }
}
