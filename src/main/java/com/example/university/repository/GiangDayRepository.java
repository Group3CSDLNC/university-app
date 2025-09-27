package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GiangDayRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void assignGiangVien(Long maLHP, Integer maGVOld, Integer maGV, Integer soTietPhanCong) {
        String sql = "EXEC sp_AssignGiangVienLHP ?, ?, ?, ?";
        jdbcTemplate.update(sql, maLHP, maGVOld, maGV, soTietPhanCong);
    }


    /**
     * Gán giảng viên cho LHP (chỉ một GV)
     */
    public void assignTroGiangHP(Long maHP, Integer maGVChinh, Integer maTGOld, Integer maGVTroGiang) {
        String sql = "EXEC sp_AssignTroGiangHP ?, ?, ?, ?";
        jdbcTemplate.update(sql, maHP, maGVChinh, maTGOld, maGVTroGiang);
    }

}
