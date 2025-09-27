package com.example.university.service;

import com.example.university.repository.GiangDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiangDayService {

    @Autowired
    private GiangDayRepository repo;
    // Assign cả giảng viên chính + trợ giảng
    public void assignGiangVien(Long maLHP, Integer maGVOld, Integer maGV, Integer soTietPhanCong) {
        repo.assignGiangVien(maLHP, maGVOld, maGV, soTietPhanCong);
    }

    public void assignTroGiangHP(Long maHP, Integer maGVChinh, Integer maTGOld, Integer maGVTroGiang) {
        repo.assignTroGiangHP(maHP, maGVChinh, maTGOld, maGVTroGiang);
    }


}
