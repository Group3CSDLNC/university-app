package com.example.university.repository;

import com.example.university.model.DangKy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DangKyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DangKy mapRow(ResultSet rs) throws SQLException {
        DangKy dk = new DangKy();
        dk.setMaSV(rs.getInt("MaSV"));
        dk.setMaHP(rs.getInt("MaHP"));
        dk.setHocKy(String.valueOf(rs.getInt("HocKy")));
        dk.setNamHoc(rs.getString("NamHoc"));
        dk.setNgayDangKy(rs.getDate("NgayDangKy"));
        dk.setTrangThai(String.valueOf(rs.getInt("TrangThai")));
        dk.setMaLHP(Integer.parseInt(rs.getString("MaLHP")));
        return dk;
    }

    public void dangKyHoc(int maSV, String maLHP, int hocKy, String namHoc) {
        jdbcTemplate.update("EXEC sp_DangKyHoc ?, ?, ?, ?", maSV, maLHP, hocKy, namHoc);
    }
}
