package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LopHocPhanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addLopHocPhan(int maHP, int hocKy, String namHoc, int maCTDT, int siSo) {
        String sql = "EXEC sp_AddLopHocPhan @MaHP=?, @HocKy=?, @NamHoc=?, @MaCTDT=?, @SiSo=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maHP, hocKy, namHoc, maCTDT, siSo}, String.class);
    }

    public void updateLopHocPhan(String maLHP, int hocKy, String namHoc, int siSo) {
        String sql = "EXEC sp_UpdateLopHocPhan @MaLHP=?, @HocKy=?, @NamHoc=?, @SiSo=?";
        jdbcTemplate.update(sql, maLHP, hocKy, namHoc, siSo);
    }

    public void deleteLopHocPhan(String maLHP) {
        String sql = "EXEC sp_DeleteLopHocPhan @MaLHP=?";
        jdbcTemplate.update(sql, maLHP);
    }

    public List<Map<String, Object>> getLopHocPhan(Integer hocKy, String namHoc, Integer maCTDT, Integer maGV) {
        String sql = "EXEC sp_GetLopHocPhan @HocKy=?, @NamHoc=?, @MaCTDT=?, @MaGV=?";
        return jdbcTemplate.queryForList(sql, hocKy, namHoc, maCTDT, maGV);
    }
}
