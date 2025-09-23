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

    public String add(int maHP, int hocKy, String namHoc, int maCTDT, int siSo) {
        return repo.addLopHocPhan(maHP, hocKy, namHoc, maCTDT, siSo);
    }

    public void update(String maLHP, int hocKy, String namHoc, int siSo) {
        repo.updateLopHocPhan(maLHP, hocKy, namHoc, siSo);
    }

    public void delete(String maLHP) {
        repo.deleteLopHocPhan(maLHP);
    }

    public List<Map<String, Object>> list(Integer hocKy, String namHoc, Integer maCTDT, Integer maGV) {
        return repo.getLopHocPhan(hocKy, namHoc, maCTDT, maGV);
    }

    public List<LopHocPhanDTO> getAvailableLopHocPhan(int maSV, String namHoc, int hocKy, Integer maCN) {
        List<LopHocPhanDTO> list = repo.getLopHocPhanBySV(maSV, namHoc, hocKy, maCN);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (LopHocPhanDTO dto : list) {
            if (dto.getNgayBatDau() != null && dto.getSoTinChi() != null) {
                try {
                    LocalDate start = LocalDate.parse(dto.getNgayBatDau(), formatter);
                    LocalDate end = start.plusDays(dto.getSoTinChi() * 15L);
                    dto.setNgayKetThuc(end.format(formatter));
                } catch (Exception e) {
                    dto.setNgayKetThuc(null);
                }
            }
        }
        return list;
    }

}
