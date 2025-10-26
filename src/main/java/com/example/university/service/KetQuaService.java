package com.example.university.service;

import com.example.university.repository.KetQuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KetQuaService {

    @Autowired
    private KetQuaRepository repo;
    public List<Map<String, Object>> xemKetQuaHocTap(Long maSV, Long maHP, Integer maCN) {
        return repo.xemKetQuaHocTap(maSV, maHP, maCN);
    }

    public List<Map<String, Object>> tinhKetQuaHocTapTongKet(Integer maSV, Long maHP, Long maCTDT, Integer maCN) {
        return repo.tinhKetQuaHocTapTongKet(maSV, maHP, maCTDT, maCN);
    }

    public List<Map<String, Object>> tinhKetQuaHocTapTongKetV3(Long maSV, Integer maCN) {
        return repo.tinhKetQuaHocTapTongKetV3(maSV, maCN);
    }

    public List<Map<String, Object>> getSinhVienChuaCoDiem(
            Long maSV,
            Long maCN,
            Long maHP,
            Long maCTDT,
            String keyword
    ) {
        return repo.getSinhVienChuaCoDiem(maSV, maCN, maHP, maCTDT, keyword);
    }
    public List<Map<String, Object>> getDanhSachHocVien(Long maLHP, Long maSV, String keyword) {
        return repo.getDanhSachHocVien(maLHP, maSV, keyword);
    }

    public void themDiem(Long maSV, Long maHP, Double diemCC, Double diemGK, Double diemCK) {
        repo.themDiem(maSV, maHP, diemCC, diemGK, diemCK);
    }

    public void suaDiem(Long maSV, Long maHP, Integer lanThi, Double diemCC, Double diemGK, Double diemCK) {
        repo.suaDiem(maSV, maHP, lanThi, diemCC, diemGK, diemCK);
    }
}
