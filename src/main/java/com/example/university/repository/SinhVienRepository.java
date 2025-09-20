package com.example.university.repository;

import com.example.university.model.SinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class SinhVienRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SinhVien> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllSinhVien", (rs, rowNum) -> {
            SinhVien sv = new SinhVien();
            sv.setMaSV(rs.getInt("MaSV"));
            sv.setHoTen(rs.getString("HoTen"));
            sv.setNgaySinh(Date.valueOf(rs.getDate("NgaySinh").toLocalDate()));
            sv.setGioiTinh(rs.getString("GioiTinh"));
            sv.setDiaChi(rs.getString("DiaChi"));
            sv.setMaCN(rs.getInt("MaCN"));
            sv.setTinhTrang(rs.getInt("TinhTrang"));
            sv.setEmail(rs.getString("Email"));
            sv.setNamHoc(rs.getInt("NamHoc"));
            return sv;
        });
    }

    public SinhVien findById(int maSV) {
        return jdbcTemplate.queryForObject("EXEC sp_GetSinhVienById ?",
                new Object[]{maSV}, (rs, rowNum) -> {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getInt("MaSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    sv.setNgaySinh(Date.valueOf(rs.getDate("NgaySinh").toLocalDate()));
                    sv.setGioiTinh(rs.getString("GioiTinh"));
                    sv.setDiaChi(rs.getString("DiaChi"));
                    sv.setMaCN(rs.getInt("MaCN"));
                    sv.setTinhTrang(rs.getInt("TinhTrang"));
                    sv.setEmail(rs.getString("Email"));
                    sv.setNamHoc(rs.getInt("NamHoc"));
                    return sv;
                });
    }

    public void add(SinhVien sv) {
        jdbcTemplate.update("EXEC sp_ThemSinhVien ?,?,?,?,?,?,?,?",
                sv.getHoTen(), sv.getNgaySinh(), sv.getGioiTinh(), sv.getDiaChi(),
                sv.getMaCN(), sv.getTinhTrang(), sv.getEmail(), sv.getNamHoc());
    }

    public void update(SinhVien sv) {
        jdbcTemplate.update("EXEC sp_SuaSinhVien ?,?,?,?,?,?,?,?,?",
                sv.getMaSV(), sv.getHoTen(), sv.getNgaySinh(), sv.getGioiTinh(), sv.getDiaChi(),
                sv.getMaCN(), sv.getTinhTrang(), sv.getEmail(), sv.getNamHoc());
    }

    public void delete(int maSV) {
        jdbcTemplate.update("EXEC sp_XoaSinhVien ?", maSV);
    }
}
