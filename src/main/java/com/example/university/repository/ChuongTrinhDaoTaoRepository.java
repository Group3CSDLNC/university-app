package com.example.university.repository;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChuongTrinhDaoTaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChuongTrinhDaoTao mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChuongTrinhDaoTao ctdt = new ChuongTrinhDaoTao();
        ctdt.setMaCTDT(rs.getInt("MaCTDT"));
        ctdt.setTenCTDT(rs.getString("TenCTDT"));
        ctdt.setTongTinChi(rs.getInt("TongTinChi"));
        ctdt.setMaCN(rs.getInt("MaCN"));
        ctdt.setNamHoc(rs.getString("NamHoc"));
        return ctdt;
    }

    public List<ChuongTrinhDaoTao> getAll(String keyWord) {
        return jdbcTemplate.query("EXEC sp_SearchCTDT @keyWord = ?", this::mapRow, keyWord);
    }

    public ChuongTrinhDaoTao getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ChuongTrinhDaoTao WHERE MaCTDT=?", this::mapRow, id);
    }

    public int insert(ChuongTrinhDaoTao ctdt) {
        return jdbcTemplate.update(
                "EXEC sp_AddChuongTrinh ?, ?, ?, ?",
                ctdt.getTenCTDT(), ctdt.getTongTinChi(), ctdt.getMaCN(), ctdt.getNamHoc()
        );
    }

    public void update(ChuongTrinhDaoTao ctdt) {
        jdbcTemplate.update(
                "EXEC sp_UpdateChuongTrinh ?, ?, ?, ?, ?",
                ctdt.getMaCTDT(), ctdt.getTenCTDT(), ctdt.getTongTinChi(), ctdt.getMaCN(), ctdt.getNamHoc()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("EXEC sp_DeleteChuongTrinh ?", id);
    }

    public List<ChuongTrinhDaoTao> search(String keyword, Integer maNVQuanLy) {
        return jdbcTemplate.query(
                        "EXEC sp_ListCTDT @MaNVQuanLy=?, @NamHoc=?, @MaKhoa=?",
                        this::mapRow,
                        maNVQuanLy, null, null
                ).stream()
                .filter(ct -> ct.getTenCTDT().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}


