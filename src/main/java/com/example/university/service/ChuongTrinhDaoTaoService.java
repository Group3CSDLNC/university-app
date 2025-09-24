package com.example.university.service;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.SessionUser;
import com.example.university.repository.ChuongTrinhDaoTaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChuongTrinhDaoTaoService {

    @Autowired
    private ChuongTrinhDaoTaoRepository repository;

    // Thêm CTDT
    public void addChuongTrinh(String tenCTDT, Integer tongTinChi, Long maCN, String namHoc) {
        repository.addChuongTrinh(tenCTDT, tongTinChi, maCN, namHoc);
    }

    // Cập nhật CTDT
    public void updateChuongTrinh(Long maCTDT, String tenCTDT, Integer tongTinChi, Long maCN, String namHoc) {
        repository.updateChuongTrinh(maCTDT, tenCTDT, tongTinChi, maCN, namHoc);
    }

    // Xóa CTDT
    public void deleteChuongTrinh(Long maCTDT) {
        repository.deleteChuongTrinh(maCTDT);
    }

    // Gán nhân viên quản lý
    public void assignNhanVienQuanLy(Long maCTDT, Integer maNV) {
        repository.assignNhanVienQuanLy(maCTDT, maNV);
    }

    // Lấy danh sách CTDT
    public List<ChuongTrinhDaoTao> listCTDT(Integer maKhoa, String namHoc, Integer maNVQuanLy) {
        return repository.listCTDT(maKhoa, namHoc, maNVQuanLy);
    }

    // Tìm kiếm CTDT
    public List<ChuongTrinhDaoTao> searchCTDT(String keyword) {
        return repository.searchCTDT(keyword);
    }
    public List<ChuongTrinhDaoTao> getAll(String keyWord) {
        return repository.getAll(keyWord);
    }
    public Map<String, Object> getQuanLyCTDT(Long maCTDT) {
        return repository.getQuanLyCTDT(maCTDT);
    }
}


