package com.example.university.service;

import com.example.university.dto.LopHocPhanDTO;
import com.example.university.repository.LopHocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class LopHocPhanService {

    @Autowired
    private LopHocPhanRepository repo;

    public Long add(Long maHP, int hocKy, String namHoc, Long maCTDT, int siSo) {
        return repo.addLopHocPhan(maHP, hocKy, namHoc, maCTDT, siSo);
    }

    public void update(Long maLHP, Long maHP, Long maCTDT, int hocKy, String namHoc, int siSo) {
        repo.updateLopHocPhan(maLHP, maHP, maCTDT, hocKy, namHoc, siSo);
    }

    public void delete(Long maLHP) {
        repo.deleteLopHocPhan(maLHP);
    }

    public List<Map<String, Object>> list(Integer hocKy, String namHoc, Long maCTDT, Integer maGV) {
        return repo.getLopHocPhan(hocKy, namHoc, maCTDT, maGV);
    }

    public List<Map<String, Object>> getLopHocPhan(
            Integer hocKy, String namHoc, Long maCTDT, Integer maGV, Long maHP) {
        if (namHoc != null && namHoc.trim().isEmpty()) {
            namHoc = null;
        }
        return repo.getLopHocPhanV2(hocKy, namHoc, maCTDT, maGV, maHP);
    }

    public List<LopHocPhanDTO> getAvailableLopHocPhan(Long maSV, String namHoc, int hocKy, Long maCN) {
        return repo.getLopHocPhanBySV(maSV, namHoc, hocKy, maCN);
    }

    public void assignGiangVien(Long maLHP, Long maHP, Integer maGVChinh, Integer soTietPhanCong, Integer maGVTroGiang) {
        repo.assignGiangVien(maLHP, maHP, maGVChinh, soTietPhanCong, maGVTroGiang);
    }

}
