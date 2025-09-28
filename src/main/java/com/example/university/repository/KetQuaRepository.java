package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class KetQuaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // Xem chi tiết kết quả học tập (danh sách các môn)
    public List<Map<String, Object>> xemKetQuaHocTap(Long maSV, Long maHP, Integer maCN) {
        String sql = "EXEC sp_XemKetQuaHocTap_v2 @MaSV=?, @MaHP=?, @MaCN=?";
        return jdbcTemplate.queryForList(sql, maSV, maHP, maCN);
    }

    // Tính kết quả tổng kết cuối cùng (GPA, tổng tín chỉ, trạng thái)
    public List<Map<String, Object>> tinhKetQuaHocTapTongKet(Integer maSV, Long maHP, Long maCTDT, Integer maCN) {
        String sql = "EXEC sp_TinhKetQuaHocTapTongKet @MaSV=?, @MaHP=?, @MaCTDT=?, @maCN=?";
        return jdbcTemplate.queryForList(sql, maSV, maHP, maCTDT, maCN);
    }

    public List<Map<String, Object>> tinhKetQuaHocTapTongKetV3(Long maSV, Integer maCN) {
        String sql = "EXEC sp_TinhKetQuaHocTapTongKet_v3 @MaSV=?, @MaCN=?";
        return jdbcTemplate.queryForList(sql, maSV, maCN);
    }

    // Lấy danh sách sinh viên đã đăng ký lớp nhưng chưa có điểm
    public List<Map<String, Object>> getSinhVienChuaCoDiem(
            Long maSV,
            Long maCN,
            Long maHP,
            Long maCTDT,
            String keyword
    ) {
        String sql = "EXEC sp_GetSinhVienChuaCoDiem @MaSV=?, @MaCN=?, @MaHP=?, @MaCTDT=?, @Keyword=?";
        return jdbcTemplate.queryForList(sql, maSV, maCN, maHP, maCTDT, keyword);
    }
}
