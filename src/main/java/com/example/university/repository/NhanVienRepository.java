package com.example.university.repository;

import com.example.university.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NhanVienRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Lấy tất cả nhân viên
    public List<NhanVien> findAll() {
        String sql = "EXEC sp_LietKeNhanVien";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> mapRow(rs));
    }

    // Thêm nhân viên
    public void save(NhanVien nv) {
        String sql = "EXEC sp_ThemNhanVien ?, ?, ?, ?, ?, ?";
        jdbcTemplate.update(sql,
                nv.getHoTen(),
                nv.getHocVi(),
                nv.getVaiTro(),
                nv.getMaQuanLy(),
                nv.getEmail(),
                nv.getSdt());
    }

    // Cập nhật nhân viên
    public void update(NhanVien nv) {
        String sql = "EXEC sp_SuaNhanVien ?, ?, ?, ?, ?, ?, ?";
        jdbcTemplate.update(sql,
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getHocVi(),
                nv.getVaiTro(),
                nv.getMaQuanLy(),
                nv.getEmail(),
                nv.getSdt());
    }

    // Xóa nhân viên
    public void delete(int maNV) {
        String sql = "EXEC sp_XoaNhanVien ?";
        jdbcTemplate.update(sql, maNV);
    }

    // Tìm theo ID
    public NhanVien findById(int maNV) {
        String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRow(rs), maNV);
    }

    // Map dữ liệu từ ResultSet → Entity
    private NhanVien mapRow(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getInt("MaNV"));
        nv.setHoTen(rs.getString("HoTen"));
        nv.setHocVi(rs.getString("HocVi"));
        nv.setVaiTro(rs.getString("VaiTro"));
        nv.setMaQuanLy(rs.getInt("MaQuanLy"));
        nv.setEmail(rs.getString("Email"));
        nv.setSdt(rs.getString("Sdt"));
        return nv;
    }

}
