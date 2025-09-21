package com.example.university.repository;

import com.example.university.model.ChuyenNganh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChuyenNganhRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Ánh xạ kết quả query -> ChuyenNganh object
    private RowMapper<ChuyenNganh> rowMapper = new RowMapper<ChuyenNganh>() {
        @Override
        public ChuyenNganh mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChuyenNganh cn = new ChuyenNganh();
            cn.setMaCN(rs.getInt("MaCN"));
            cn.setTenCN(rs.getString("TenCN"));
            cn.setMaKhoa(rs.getInt("MaKhoa"));
            return cn;
        }
    };

    // Insert
    public Integer insert(String tenCN, Integer maKhoa) {
        return jdbcTemplate.queryForObject(
                "EXEC sp_InsertChuyenNganh @TenCN = ?, @MaKhoa = ?",
                new Object[]{tenCN, maKhoa},
                Integer.class
        );
    }

    // Update
    public void update(Integer maCN, String tenCN, Integer maKhoa) {
        jdbcTemplate.update(
                "EXEC sp_UpdateChuyenNganh @MaCN = ?, @TenCN = ?, @MaKhoa = ?",
                maCN, tenCN, maKhoa
        );
    }

    // Delete
    public void delete(Integer maCN) {
        jdbcTemplate.update(
                "EXEC sp_DeleteChuyenNganh @MaCN = ?",
                maCN
        );
    }

    // GetAll
    public List<ChuyenNganh> getAll() {
        return jdbcTemplate.query(
                "EXEC sp_GetAllChuyenNganh",
                rowMapper
        );
    }

    // GetById
    public ChuyenNganh getById(Integer maCN) {
        return jdbcTemplate.queryForObject(
                "EXEC sp_GetChuyenNganhById @MaCN = ?",
                new Object[]{maCN},
                rowMapper
        );
    }
}
