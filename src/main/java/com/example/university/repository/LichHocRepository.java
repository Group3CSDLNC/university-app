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

    public List<LichHoc> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllLichHoc", (rs, rowNum) -> {
            LichHoc lh = new LichHoc();
            lh.setMaLichHoc(rs.getInt("MaLichHoc"));
            lh.setMaLHP(rs.getString("MaLHP"));
            lh.setNgay(Date.valueOf(rs.getDate("Ngay").toLocalDate()));
            lh.setMaPH(rs.getString("MaPH"));
            lh.setSoTiet(rs.getInt("SoTiet"));
            return lh;
        });
    }

    public void add(LichHoc lh) {
        jdbcTemplate.update("EXEC sp_ThemLichHoc ?,?,?,?",
                lh.getMaLHP(), lh.getNgay(), lh.getMaPH(), lh.getSoTiet());
    }

    public void update(LichHoc lh) {
        jdbcTemplate.update("EXEC sp_SuaLichHoc ?,?,?,?,?",
                lh.getMaLichHoc(), lh.getMaLHP(), lh.getNgay(), lh.getMaPH(), lh.getSoTiet());
    }

    public void delete(int maLichHoc) {
        jdbcTemplate.update("EXEC sp_XoaLichHoc ?", maLichHoc);
    }
}
