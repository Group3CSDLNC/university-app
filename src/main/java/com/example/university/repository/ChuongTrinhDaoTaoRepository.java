package com.example.university.repository;

import com.example.university.model.CTDTNhanVien;
import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.NhanVien;
import com.example.university.model.SessionUser;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ChuongTrinhDaoTaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChuongTrinhDaoTao mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChuongTrinhDaoTao ctdt = new ChuongTrinhDaoTao();
        ctdt.setMaCTDT(rs.getLong("MaCTDT"));
        ctdt.setTenCTDT(rs.getString("TenCTDT"));
        ctdt.setTongTinChi(rs.getInt("TongTinChi"));
        ctdt.setMaCN(rs.getLong("MaCN"));
        ctdt.setNamHoc(rs.getString("NamHoc"));
        return ctdt;
    }

    // Thêm chương trình đào tạo
    public void addChuongTrinh(String tenCTDT, Integer tongTinChi, Long maCN, String namHoc) {
        jdbcTemplate.update(
                "EXEC sp_AddChuongTrinh @TenCTDT = ?, @TongTinChi = ?, @MaCN = ?, @NamHoc = ?",
                tenCTDT, tongTinChi, maCN, namHoc
        );
    }

    // Cập nhật chương trình đào tạo
    public void updateChuongTrinh(Long maCTDT, String tenCTDT, Integer tongTinChi, Long maCN, String namHoc) {
        jdbcTemplate.update(
                "EXEC sp_UpdateChuongTrinh @MaCTDT = ?, @TenCTDT = ?, @TongTinChi = ?, @MaCN = ?, @NamHoc = ?",
                maCTDT, tenCTDT, tongTinChi, maCN, namHoc
        );
    }

    // Xóa chương trình đào tạo
    public void deleteChuongTrinh(Long maCTDT) {
        jdbcTemplate.update(
                "EXEC sp_DeleteChuongTrinh @MaCTDT = ?",
                maCTDT
        );
    }

    // Gán nhân viên quản lý cho chương trình đào tạo
    public void assignNhanVienQuanLy(Long maCTDT, Integer maNV) {
        jdbcTemplate.update(
                "EXEC sp_AssignNVQuanLyCTDT @MaCTDT = ?, @MaNV = ?",
                maCTDT, maNV
        );
    }

    public List<ChuongTrinhDaoTao> listCTDT(Integer maKhoa, String namHoc, Integer maNVQuanLy) {
        // Tạo danh sách tham số và placeholder
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("EXEC sp_ListCTDT ");

        if (maKhoa != null) {
            sql.append("@MaKhoa = ?, ");
            params.add(maKhoa);
        }
        if (namHoc != null && !namHoc.isEmpty()) {
            sql.append("@NamHoc = ?, ");
            params.add(namHoc);
        }
        if (maNVQuanLy != null) {
            sql.append("@MaNVQuanLy = ?, ");
            params.add(maNVQuanLy);
        }

        // Xóa dấu phẩy cuối cùng nếu có
        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                (rs, rowNum) -> {
                    ChuongTrinhDaoTao ctdt = new ChuongTrinhDaoTao();
                    ctdt.setMaCTDT(rs.getLong("MaCTDT"));
                    ctdt.setTenCTDT(rs.getString("TenCTDT"));
                    ctdt.setTongTinChi(rs.getInt("TongTinChi"));
                    ctdt.setMaCN(rs.getLong("MaCN"));
                    ctdt.setNamHoc(rs.getString("NamHoc"));
                    return ctdt;
                }
        );
    }


    // Tìm kiếm chương trình đào tạo theo keyword
    public List<ChuongTrinhDaoTao> searchCTDT(String keyword) {
        return jdbcTemplate.query(
                "EXEC sp_SearchCTDT @Keyword = ?",
                new Object[]{keyword},
                (rs, rowNum) -> {
                    ChuongTrinhDaoTao ctdt = new ChuongTrinhDaoTao();
                    ctdt.setMaCTDT(rs.getLong("MaCTDT"));
                    ctdt.setTenCTDT(rs.getString("TenCTDT"));
                    ctdt.setTongTinChi(rs.getInt("TongTinChi"));
                    ctdt.setMaCN(rs.getLong("MaCN"));
                    ctdt.setNamHoc(rs.getString("NamHoc"));
                    return ctdt;
                }
        );
    }

    public List<ChuongTrinhDaoTao> getAll(String keyWord) {
        return jdbcTemplate.query("EXEC sp_SearchCTDT @keyWord = ?", this::mapRow, keyWord);
    }

    public Map<String, Object> getQuanLyCTDT(Long maCTDT) {
        String sql = "EXEC sp_GetQuanLyCTDT @MaCTDT = ?";
        return jdbcTemplate.queryForMap(sql, maCTDT);
    }
}