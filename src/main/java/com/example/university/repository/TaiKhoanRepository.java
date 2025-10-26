package com.example.university.repository;

import com.example.university.dto.TaiKhoanDTO;
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
                tk.setMaSV(rs.getLong("MaSV"));
                tk.setMaNV(rs.getInt("MaNV"));
                return tk;
            }
            return null;
        });
    }
    public TaiKhoanDTO getTaiKhoanById(int maTK) {
        String sql = "EXEC sp_GetTaiKhoanById @MaTK = ?";
        return jdbcTemplate.query(sql, new Object[]{maTK}, (ResultSet rs) -> {
            if (rs.next()) {
                TaiKhoanDTO dto = new TaiKhoanDTO();
                dto.setMaTK(rs.getInt("MaTK"));
                dto.setTenDangNhap(rs.getString("TenDangNhap"));
                dto.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
                dto.setMaSV(rs.getObject("MaSV") != null ? rs.getInt("MaSV") : null);
                dto.setTenSinhVien(rs.getString("TenSinhVien"));
                dto.setEmailSinhVien(rs.getString("EmailSinhVien"));
                dto.setNgaySinh(rs.getDate("NgaySinh") != null ? rs.getDate("NgaySinh").toLocalDate() : null);
                dto.setGioiTinh(rs.getString("GioiTinh"));
                dto.setChuyenNganh(rs.getString("ChuyenNganh"));
                dto.setMaNV(rs.getObject("MaNV") != null ? rs.getInt("MaNV") : null);
                dto.setTenNhanVien(rs.getString("TenNhanVien"));
                dto.setEmailNhanVien(rs.getString("EmailNhanVien"));
                dto.setHocVi(rs.getString("HocVi"));
                dto.setVaiTro(rs.getString("VaiTro"));
                return dto;
            }
            return null;
        });
    }
}
