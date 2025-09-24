package com.example.university.repository;

import com.example.university.model.LichHoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class LichHocRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Lấy toàn bộ lịch học
    public List<LichHoc> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllLichHoc", (rs, rowNum) -> {
            LichHoc lh = new LichHoc();
            lh.setMaLichHoc(rs.getInt("MaLichHoc"));
            lh.setMaLHP(rs.getLong("MaLHP"));
            lh.setTietBatDau(rs.getInt("TietBatDau"));
            lh.setMaPH(rs.getInt("MaPH"));
            lh.setSoTiet(rs.getInt("SoTiet"));
            lh.setThuTrongTuan(rs.getInt("ThuTrongTuan"));
            lh.setNgayBatDau(rs.getDate("NgayBatDau"));
            lh.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            return lh;
        });
    }

    // Thêm lịch học
    public int add(LichHoc lh) {
        return jdbcTemplate.update(
                "EXEC sp_ThemLichHoc ?,?,?,?,?,?,?",
                lh.getMaLHP(),
                lh.getTietBatDau(),
                lh.getMaPH(),
                lh.getSoTiet(),
                lh.getThuTrongTuan(),
                lh.getNgayBatDau() != null ? new Date(lh.getNgayBatDau().getTime()) : null,
                lh.getNgayKetThuc() != null ? new Date(lh.getNgayKetThuc().getTime()) : null
        );
    }

    // Cập nhật lịch học
    public int update(LichHoc lh) {
        return jdbcTemplate.update(
                "EXEC sp_SuaLichHoc ?,?,?,?,?,?,?,?",
                lh.getMaLichHoc(),
                lh.getMaLHP(),
                lh.getTietBatDau(),
                lh.getMaPH(),
                lh.getSoTiet(),
                lh.getThuTrongTuan(),
                lh.getNgayBatDau() != null ? new Date(lh.getNgayBatDau().getTime()) : null,
                lh.getNgayKetThuc() != null ? new Date(lh.getNgayKetThuc().getTime()) : null
        );
    }

    // Xóa lịch học
    public int delete(int maLichHoc) {
        return jdbcTemplate.update(
                "EXEC sp_XoaLichHoc ?",
                maLichHoc
        );
    }
}
