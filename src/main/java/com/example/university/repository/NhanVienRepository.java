package com.example.university.repository;

import com.example.university.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NhanVienRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NhanVien> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllNhanVien", (rs, rowNum) -> {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("MaNV"));
            nv.setHoTen(rs.getString("HoTen"));
            nv.setHocVi(rs.getString("HocVi"));
            nv.setVaiTro(rs.getString("VaiTro"));
            nv.setEmail(rs.getString("Email"));
            return nv;
        });
    }

    public NhanVien findById(int maNV) {
        return jdbcTemplate.queryForObject("EXEC sp_GetNhanVienById ?",
                new Object[]{maNV}, (rs, rowNum) -> {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getInt("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setHocVi(rs.getString("HocVi"));
                    nv.setVaiTro(rs.getString("VaiTro"));
                    nv.setEmail(rs.getString("Email"));
                    return nv;
                });
    }

    public void add(NhanVien nv) {
        jdbcTemplate.update("EXEC sp_ThemNhanVien ?,?,?,?",
                nv.getHoTen(), nv.getHocVi(), nv.getVaiTro(), nv.getEmail());
    }

    public void update(NhanVien nv) {
        jdbcTemplate.update("EXEC sp_SuaNhanVien ?,?,?,?,?",
                nv.getMaNV(), nv.getHoTen(), nv.getHocVi(), nv.getVaiTro(), nv.getEmail());
    }

    public void delete(int maNV) {
        jdbcTemplate.update("EXEC sp_XoaNhanVien ?", maNV);
    }
}

