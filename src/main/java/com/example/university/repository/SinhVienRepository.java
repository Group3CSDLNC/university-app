package com.example.university.repository;

import com.example.university.model.SinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SinhVienRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SinhVien mapRow(ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMaSV(rs.getInt("MaSV"));
        sv.setHoTen(rs.getString("HoTen"));
        sv.setNgaySinh(rs.getDate("NgaySinh"));
        sv.setGioiTinh(rs.getString("GioiTinh"));
        sv.setMaCN(rs.getInt("MaCN"));
        sv.setDiaChi(rs.getString("DiaChi"));
        sv.setTinhTrang(String.valueOf(rs.getInt("TinhTrang")));
        sv.setEmail(rs.getString("Email"));
        sv.setNamHoc(String.valueOf(rs.getInt("NamHoc")));
        return sv;
    }

    public List<SinhVien> findAll() {
        String sql = "EXEC sp_LietKeHocVien";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    public SinhVien findById(int maSV) {
        String sql = "SELECT * FROM sinhvien WHERE MaSV = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRow(rs), maSV);
    }

    public void save(SinhVien sv) {
        String sql = "EXEC sp_ThemHocVien ?, ?, ?, ?, ?, ?, ?, ?";
        jdbcTemplate.update(sql,
                sv.getHoTen(),
                sv.getNgaySinh(),
                sv.getGioiTinh(),
                sv.getMaCN(),
                sv.getDiaChi(),
                sv.getTinhTrang(),
                sv.getEmail(),
                sv.getNamHoc());
    }

    public void update(SinhVien sv) {
        String sql = "EXEC sp_SuaHocVien ?, ?, ?, ?, ?, ?, ?, ?, ?";
        jdbcTemplate.update(sql,
                sv.getMaSV(),
                sv.getHoTen(),
                sv.getNgaySinh(),
                sv.getGioiTinh(),
                sv.getMaCN(),
                sv.getDiaChi(),
                sv.getTinhTrang(),
                sv.getEmail(),
                sv.getNamHoc());
    }

    public void delete(int maSV) {
        String sql = "EXEC sp_XoaHocVien ?";
        jdbcTemplate.update(sql, maSV);
    }
}
