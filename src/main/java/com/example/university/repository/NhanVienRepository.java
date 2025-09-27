package com.example.university.repository;

import com.example.university.dto.LuongGVDTO;
import com.example.university.model.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NhanVienRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<NhanVien> findAll() {
        return jdbcTemplate.query("EXEC sp_GetAllNhanVien", (rs, rowNum) -> {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("MaNV"));
            nv.setHoTen(rs.getString("HoTen"));
            nv.setHocVi(rs.getString("HocVi"));
            nv.setVaiTro(rs.getString("VaiTro"));
            nv.setEmail(rs.getString("Email"));
            return nv;
        });
    }

    public List<NhanVien> findByVaiTro(List<String> vaiTros) {
        if (vaiTros == null || vaiTros.isEmpty()) {
            return new ArrayList<>(); // trả về rỗng nếu danh sách null hoặc trống
        }

        // Chuyển List<String> thành CSV
        String csv = vaiTros.stream()
                .filter(StringUtils::hasText)
                .collect(Collectors.joining(","));

        String sql = "EXEC sp_GetNhanVienByVaiTroList ?";

        return jdbcTemplate.query(sql, new Object[]{csv}, (rs, rowNum) -> {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("MaNV"));
            nv.setHoTen(rs.getString("HoTen"));
            nv.setHocVi(rs.getString("HocVi"));
            nv.setVaiTro(rs.getString("VaiTro"));
            nv.setEmail(rs.getString("Email"));
            return nv;
        });
    }
    public NhanVien findById(int maNV) {
        return jdbcTemplate.queryForObject("EXEC sp_GetNhanVienById ?",
                new Object[]{maNV}, (rs, rowNum) -> {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getInt("MaNV"));
                    nv.setHoTen(rs.getString("HoTen"));
                    nv.setHocVi(rs.getString("HocVi"));
                    nv.setVaiTro(rs.getString("VaiTro"));
                    nv.setEmail(rs.getString("Email"));
                    return nv;
                });
    }

    public void add(NhanVien nv) {
        jdbcTemplate.update("EXEC sp_ThemNhanVien ?,?,?,?",
                nv.getHoTen(), nv.getHocVi(), nv.getVaiTro(), nv.getEmail());
    }

    public void update(NhanVien nv) {
        jdbcTemplate.update("EXEC sp_SuaNhanVien ?,?,?,?,?",
                nv.getMaNV(), nv.getHoTen(), nv.getHocVi(), nv.getVaiTro(), nv.getEmail());
    }

    public void delete(int maNV) {
        jdbcTemplate.update("EXEC sp_XoaNhanVien ?", maNV);
    }

    public List<LuongGVDTO> danhSachLuongGV(int thang, int nam) {
        String sql = "EXEC sp_DanhSachLuongGiangVien @Thang=?, @Nam=?";
        return jdbcTemplate.query(sql, new Object[]{thang, nam},
                (rs, rowNum) -> {
                    LuongGVDTO dto = new LuongGVDTO();
                    dto.setMaNV(rs.getInt("MaNV"));
                    dto.setHoTen(rs.getString("HoTen"));
                    dto.setTongGioDay(rs.getInt("TongGioDay"));
                    dto.setLuong(rs.getBigDecimal("Luong"));
                    return dto;
                });
    }

    // Giảng viên chính
    public List<NhanVien> getGiangVienChinhByLHP(Long maLHP) {
        String sql = "EXEC sp_GetGiangVienChinhByLHP ?";
        return jdbcTemplate.query(sql, new Object[]{maLHP}, (rs, rowNum) -> {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("MaNV"));
            nv.setHoTen(rs.getString("HoTen"));
            nv.setHocVi(rs.getString("HocVi"));
            nv.setVaiTro(rs.getString("VaiTro"));
            nv.setEmail(rs.getString("Email"));
            return nv;
        });
    }

    // Trợ giảng
    public List<NhanVien> getTroGiangByLHP(Long maLHP) {
        String sql = "EXEC sp_GetTroGiangByLHP ?";
        return jdbcTemplate.query(sql, new Object[]{maLHP}, (rs, rowNum) -> {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getInt("MaNV"));
            nv.setHoTen(rs.getString("HoTen"));
            nv.setHocVi(rs.getString("HocVi"));
            nv.setVaiTro(rs.getString("VaiTro"));
            nv.setEmail(rs.getString("Email"));
            return nv;
        });
    }

}

