package com.example.university.repository;

import com.example.university.dto.SinhVienDTO;
import com.example.university.model.SinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SinhVienRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SinhVien findById(Long maSV) {
        return jdbcTemplate.queryForObject("EXEC sp_GetSinhVienById ?",
                new Object[]{maSV}, (rs, rowNum) -> {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getLong("MaSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    sv.setNgaySinh(rs.getString("NgaySinh"));
                    sv.setGioiTinh(rs.getString("GioiTinh"));
                    sv.setDiaChi(rs.getString("DiaChi"));
                    sv.setMaCN(rs.getLong("MaCN"));
                    sv.setTinhTrang(rs.getInt("TinhTrang"));
                    sv.setEmail(rs.getString("Email"));
                    sv.setNamHoc(rs.getInt("NamHoc"));
                    return sv;
                });
    }

    public void add(SinhVien sv) {
        jdbcTemplate.update("EXEC sp_ThemSinhVien ?,?,?,?,?,?,?,?,?",
                sv.getMaSV(),
                sv.getHoTen(),
                sv.getNgaySinh(),
                sv.getGioiTinh(),
                sv.getDiaChi(),
                sv.getMaCN(),
                sv.getTinhTrang(),
                sv.getEmail(),
                sv.getNamHoc()
        );
    }

    public void update(SinhVien sv) {
        jdbcTemplate.update("EXEC sp_SuaSinhVien ?,?,?,?,?,?,?,?,?",
                sv.getMaSV(), sv.getHoTen(), sv.getNgaySinh(), sv.getGioiTinh(), sv.getDiaChi(),
                sv.getMaCN(), sv.getTinhTrang(), sv.getEmail(), sv.getNamHoc());
    }

    public void delete(Long maSV) {
        jdbcTemplate.update("EXEC sp_XoaSinhVien ?", maSV);
    }

    public Integer maxSeq(SinhVien sv) {
        String sqlMaxSeq = "SELECT ISNULL(MAX(CAST(RIGHT(CAST(MaSV AS VARCHAR(20)),3) AS INT)),0) " +
                "FROM SinhVien WHERE MaCN = ? AND NamHoc = ?";
        Integer maxSeq = jdbcTemplate.queryForObject(sqlMaxSeq, Integer.class, sv.getMaCN(), sv.getNamHoc());
        return maxSeq;
    }

    private SinhVienDTO mapRow(ResultSet rs) throws SQLException {
        SinhVienDTO sv = new SinhVienDTO();
        sv.setMaSV(rs.getLong("MaSV"));
        sv.setHoTen(rs.getString("HoTen"));
        sv.setNgaySinh(String.valueOf(rs.getDate("NgaySinh")));
        sv.setGioiTinh(rs.getString("GioiTinh"));
        sv.setDiaChi(rs.getString("DiaChi"));
        sv.setMaCN(rs.getLong("MaCN"));
        sv.setTinhTrang(rs.getInt("TinhTrang"));
        sv.setEmail(rs.getString("Email"));
        sv.setNamHoc(rs.getInt("NamHoc"));
        try {
            sv.setTenCN(rs.getString("TenCN"));
        } catch (Exception ignored) {
        }
        return sv;
    }


    public List<SinhVienDTO> findAllSV() {
        return jdbcTemplate.query("SELECT sv.*, cn.TenCN FROM SinhVien sv " +
                "JOIN ChuyenNganh cn ON sv.MaCN = cn.MaCN", (rs, rowNum) -> mapRow(rs));
    }

    public List<SinhVien> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllSinhVien", (rs, rowNum) -> {
            SinhVien sv = new SinhVien();
            sv.setMaSV(rs.getLong("MaSV"));
            sv.setHoTen(rs.getString("HoTen"));
            sv.setNgaySinh(rs.getString("NgaySinh"));
            sv.setGioiTinh(rs.getString("GioiTinh"));
            sv.setDiaChi(rs.getString("DiaChi"));
            sv.setMaCN(rs.getLong("MaCN"));
            sv.setTinhTrang(rs.getInt("TinhTrang"));
            sv.setEmail(rs.getString("Email"));
            sv.setNamHoc(rs.getInt("NamHoc"));
            return sv;
        });
    }
    public SinhVienDTO getById(Long maSV) {
        return jdbcTemplate.queryForObject(
                "EXEC sp_GetSinhVienById ?",
                (rs, rowNum) -> {
                    SinhVienDTO sv = new SinhVienDTO();
                    sv.setMaSV(rs.getLong("MaSV"));
                    sv.setHoTen(rs.getString("HoTen"));
                    sv.setNgaySinh(rs.getString("NgaySinh"));
                    sv.setGioiTinh(rs.getString("GioiTinh"));
                    sv.setDiaChi(rs.getString("DiaChi"));
                    sv.setMaCN(rs.getLong("MaCN"));
                    sv.setTinhTrang(rs.getInt("TinhTrang"));
                    sv.setEmail(rs.getString("Email"));
                    sv.setNamHoc(rs.getInt("NamHoc"));
                    return sv;
                },
                maSV
        );
    }

}
