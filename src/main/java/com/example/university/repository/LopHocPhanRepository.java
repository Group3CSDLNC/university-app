package com.example.university.repository;

import com.example.university.dto.LopHocPhanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LopHocPhanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long addLopHocPhan(Long maHP, int hocKy, String namHoc, Long maCTDT, int siSo) {
        String sql = "EXEC sp_AddLopHocPhan @MaHP=?, @HocKy=?, @NamHoc=?, @MaCTDT=?, @SiSo=?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{maHP, hocKy, namHoc, maCTDT, siSo},
                Long.class
        );
    }


    public void updateLopHocPhan(Long maLHP, Long maHP, Long maCTDT, int hocKy, String namHoc, int siSo) {
        String sql = "EXEC sp_UpdateLopHocPhanV2 @MaLHP=?, @maHP=?, @maCTDT=?, @HocKy=?, @NamHoc=?, @SiSo=?";
        jdbcTemplate.update(sql, maLHP, maHP, maCTDT, hocKy, namHoc, siSo);
    }

    public void deleteLopHocPhan(Long maLHP) {
        String sql = "EXEC sp_DeleteLopHocPhan @MaLHP=?";
        jdbcTemplate.update(sql, maLHP);
    }

    public List<Map<String, Object>> getLopHocPhan(Integer hocKy, String namHoc, Long maCTDT, Integer maGV) {
        String sql = "EXEC sp_GetLopHocPhan @HocKy=?, @NamHoc=?, @MaCTDT=?, @MaGV=?";
        return jdbcTemplate.queryForList(sql, hocKy, namHoc, maCTDT, maGV);
    }

    public List<Map<String, Object>> getLopHocPhanV2(
            Integer hocKy, String namHoc, Long maCTDT, Integer maGV, Long maHP) {

        String sql = "EXEC sp_GetLopHocPhanV2 @HocKy = ?, @NamHoc = ?, @MaCTDT = ?, @MaGV = ?, @MaHP = ?";

        return jdbcTemplate.queryForList(sql, hocKy, namHoc, maCTDT, maGV, maHP);
    }
    public List<LopHocPhanDTO> getLopHocPhanBySV(Long maSV, String namHoc, int hocKy, Long maCN) {
        String sql = "EXEC sp_GetLopHocPhanBySV @MaSV=?, @NamHoc=?, @HocKy=?, @MaCN=?";
        return jdbcTemplate.query(
                sql,
                new Object[]{maSV, namHoc, hocKy, maCN},
                (rs, rowNum) -> {
                    LopHocPhanDTO dto = new LopHocPhanDTO();
                    dto.setMaLHP(rs.getString("MaLHP"));
                    dto.setMaHP(rs.getInt("MaHP"));
                    dto.setTenHP(rs.getString("TenHP"));
                    dto.setSoTinChi(rs.getInt("SoTinChi"));
                    dto.setTietLT(rs.getInt("TietLT"));
                    dto.setTietTH(rs.getInt("TietTH"));
                    dto.setSiSoToiDa(rs.getInt("SiSoToiDa"));
                    dto.setSoLuongDaDangKy(rs.getInt("SoLuongDaDangKy"));
                    dto.setSlotConLai(rs.getInt("SlotConLai"));
                    dto.setNamHoc(rs.getString("NamHoc"));
                    dto.setHocKy(rs.getInt("HocKy"));
                    dto.setPhongHoc(rs.getString("PhongHoc"));
                    dto.setTietBatDau(rs.getInt("TietBatDau"));
                    dto.setThu(rs.getInt("ThuTrongTuan"));   // thêm map cột mới
                    dto.setNgayBatDau(rs.getString("NgayBatDau"));
                    dto.setNgayKetThuc(rs.getString("NgayKetThuc"));
                    dto.setGiangVienChinh(rs.getString("GiangVienChinh"));
                    dto.setTroGiang(rs.getString("TroGiang"));
                    dto.setTenChuyenNganh(rs.getString("TenChuyenNganh"));
                    dto.setTenChuongTrinhDaoTao(rs.getString("TenChuongTrinhDaoTao"));
                    dto.setIsDangKy(rs.getBoolean("IsDangKy"));
                    return dto;
                }
        );
    }
    public void assignGiangVien(Long maLHP, Long maHP, Integer maGVChinh, Integer soTietPhanCong, Integer maGVTroGiang) {
        String sql = "EXEC sp_AssignGiangVien @MaLHP=?, @MaHP=?, @MaGVChinh=?, @SoTietPhanCong=?, @MaGVTroGiang=?";
        jdbcTemplate.update(sql, maLHP, maHP, maGVChinh, soTietPhanCong, maGVTroGiang);
    }
}
