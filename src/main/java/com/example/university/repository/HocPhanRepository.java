package com.example.university.repository;

import com.example.university.model.HocPhan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HocPhanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper để map ResultSet -> HocPhan object
    private RowMapper<HocPhan> hocPhanMapper = new RowMapper<HocPhan>() {
        @Override
        public HocPhan mapRow(ResultSet rs, int rowNum) throws SQLException {
            HocPhan hp = new HocPhan();
            hp.setMaHP(rs.getInt("MaHP"));
            hp.setTenHP(rs.getString("TenHP"));
            hp.setSoTinChi(rs.getInt("SoTinChi"));
            hp.setTietTH(rs.getInt("TietTH"));
            hp.setTietLT(rs.getInt("TietLT"));
            hp.setMaCTDT(rs.getInt("MaCTDT"));
            hp.setTienQuyet(rs.getObject("TienQuyet") != null ? rs.getInt("TienQuyet") : null);
            return hp;
        }
    };

    // CREATE
    public void insert(HocPhan hocPhan) {
        // Lấy MaHP lớn nhất hiện tại
        Integer maxId = jdbcTemplate.queryForObject(
                "SELECT ISNULL(MAX(MaHP), 0) FROM HocPhan",
                Integer.class
        );

        // Gán MaHP mới = maxId + 1
        int newId = maxId + 1;
        hocPhan.setMaHP(newId);

        // Insert bản ghi
        jdbcTemplate.update(
                "EXEC sp_InsertHocPhan ?, ?, ?, ?, ?, ?, ?",
                hocPhan.getMaHP(),
                hocPhan.getTenHP(),
                hocPhan.getSoTinChi(),
                hocPhan.getTietTH(),
                hocPhan.getTietLT(),
                hocPhan.getMaCTDT(),
                hocPhan.getTienQuyet()
        );
    }


    // UPDATE
    public void update(HocPhan hocPhan) {
        jdbcTemplate.update("EXEC sp_UpdateHocPhan ?, ?, ?, ?, ?, ?, ?",
                hocPhan.getMaHP(),
                hocPhan.getTenHP(),
                hocPhan.getSoTinChi(),
                hocPhan.getTietTH(),
                hocPhan.getTietLT(),
                hocPhan.getMaCTDT(),
                hocPhan.getTienQuyet()
        );
    }

    // DELETE
    public void delete(int maHP) {
        jdbcTemplate.update("EXEC HocPhan_Delete ?", maHP);
    }

    // READ by ID
    public HocPhan getById(int maHP) {
        return jdbcTemplate.queryForObject("EXEC sp_GetHocPhanById ?", new Object[]{maHP}, hocPhanMapper);
    }

    // READ all
    public List<HocPhan> getAll() {
        return jdbcTemplate.query("EXEC sp_GetAllHocPhan", hocPhanMapper);
    }

    // SEARCH by keyword
    public List<HocPhan> searchByKeyword(String keyword) {
        return jdbcTemplate.query("EXEC sp_SearchHocPhanByKeyword ?", new Object[]{keyword}, hocPhanMapper);
    }
}
