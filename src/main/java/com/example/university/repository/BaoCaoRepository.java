package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaoCaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getBangDiem(Long maSV, Long maCTDT) {
        return jdbcTemplate.queryForList(
                "EXEC sp_BangDiemSinhVien ?, ?", maSV, maCTDT
        );
    }

    public List<Map<String,Object>> getChuaHoanThanh(Long maSV, Long maCTDT) {
        return jdbcTemplate.queryForList(
                "EXEC sp_SinhVienChuaHoanThanhKhoa ?, ?",maSV, maCTDT
        );
    }

    public List<Map<String,Object>> getBaoCaoSiSo() {
        return jdbcTemplate.queryForList("EXEC sp_BaoCaoSiSoLop");
    }


}
