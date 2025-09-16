package com.example.university.repository;

import com.example.university.model.Khoa;
import com.example.university.model.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class TaiKhoanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TaiKhoanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TaiKhoan checkLogin(String tenDangNhap, String matKhau) {
        String sql = "EXEC sp_CheckLogin ?, ?";
        return jdbcTemplate.query(sql, new Object[]{tenDangNhap, matKhau}, (ResultSet rs) -> {
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getInt("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
                tk.setMaSV(rs.getInt("MaSV"));
                tk.setMaNV(rs.getInt("MaNV"));
                return tk;
            }
            return null;
        });
    }

}
