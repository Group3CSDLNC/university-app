package com.example.university.service;

import com.example.university.model.ChuyenNganh;
import com.example.university.repository.ChuyenNganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuyenNganhService {

    @Autowired
    private ChuyenNganhRepository repository;

    // Thêm mới chuyên ngành
    public Integer insert(String tenCN, Integer maKhoa) {
        return repository.insert(tenCN, maKhoa);
    }

    // Cập nhật chuyên ngành
    public void update(Integer maCN, String tenCN, Integer maKhoa) {
        repository.update(maCN, tenCN, maKhoa);
    }

    // Xóa chuyên ngành
    public void delete(Integer maCN) {
        repository.delete(maCN);
    }

    // Lấy danh sách chuyên ngành
    public List<ChuyenNganh> getAll() {
        return repository.getAll();
    }

    // Lấy 1 chuyên ngành theo ID
    public ChuyenNganh getById(Integer maCN) {
        return repository.getById(maCN);
    }
}
