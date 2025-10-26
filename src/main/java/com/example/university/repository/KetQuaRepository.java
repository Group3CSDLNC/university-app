package com.example.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    public List<Map<String, Object>> getDanhSachHocVien(Long maLHP, Long maSV, String keyword) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_LayDanhSachHocVien");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("MaLHP", maLHP);
        inParams.put("MaSV", maSV);
        inParams.put("Keyword", keyword);

        Map<String, Object> result = jdbcCall.execute(inParams);

        // Stored procedure trả về result set mặc định là "#result-set-1"
        return (List<Map<String, Object>>) result.get("#result-set-1");
    }

    public void themDiem(Long maSV, Long maHP, Double diemCC, Double diemGK, Double diemCK) {
        String sql = "EXEC sp_ThemDiem @MaSV=?, @MaHP=?, @DiemCC=?, @DiemGK=?, @DiemCK=?";
        jdbcTemplate.update(sql, maSV, maHP, diemCC, diemGK, diemCK);
    }

    public void suaDiem(Long maSV, Long maHP, Integer lanThi, Double diemCC, Double diemGK, Double diemCK) {
        String sql = "EXEC sp_SuaDiem @MaSV=?, @MaHP=?, @LanThi=?, @DiemCC=?, @DiemGK=?, @DiemCK=?";
        jdbcTemplate.update(sql, maSV, maHP, lanThi, diemCC, diemGK, diemCK);
    }
}
