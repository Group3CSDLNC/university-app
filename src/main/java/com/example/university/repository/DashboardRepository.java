package com.example.university.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getDashboardSummary() {
        String sql = "EXEC sp_dashboard_admin_summary";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tongSinhVien", rs.getInt("tong_sinh_vien"));
            map.put("tongNhanVien", rs.getInt("tong_nhan_vien"));
            map.put("tongGiangVien", rs.getInt("tong_giang_vien"));
            map.put("tongKhoa", rs.getInt("tong_khoa"));
            return map;
        });
    }

    public Map<String, Object> getGiangVienSummary(int maGV) {
        String sql = "EXEC sp_dashboard_giang_vien_summary ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{maGV}, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("hocKyHienTai", rs.getInt("hoc_ky_hien_tai"));
            map.put("namHocHienTai", rs.getString("nam_hoc_hien_tai"));
            map.put("soMonGiangDay", rs.getInt("so_mon_giang_day"));
            map.put("tongSinhVien", rs.getInt("tong_sinh_vien"));
            return map;
        });
    }
    public Map<String, Object> getSinhVienDashboard(int maSV) {
        String sql = "SELECT SoMonHoc, DiemTrungBinh FROM v_SinhVienDashboardSummary WHERE MaSV = ?";
        return jdbcTemplate.queryForMap(sql, maSV);
    }

}

