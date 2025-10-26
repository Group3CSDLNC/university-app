package com.example.university.service;

import com.example.university.dto.SinhVienDTO;
import com.example.university.model.SinhVien;
import com.example.university.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {

    @Autowired
    private SinhVienRepository repo;

    public List<SinhVien> getAll() { return repo.findAll(); }

    public SinhVien getById(Long maSV) { return repo.findById(maSV); }

    public void add(SinhVien sv) {
        // 1. Lấy max sequence cho MaCN và NamHoc
        Integer maxSeq = repo.maxSeq(sv);
        if (maxSeq == null) maxSeq = 0;

        // 2. Tính MaSV theo quy tắc: YY + MaCN(3 chữ số) + seq(3 chữ số)
        int yearSuffix = sv.getNamHoc() % 100; // 2 số cuối năm
        String maCNStr = String.format("%03d", sv.getMaCN());
        String seqStr = String.format("%03d", maxSeq + 1);
        sv.setMaSV(Long.parseLong(String.format("%02d%s%s", yearSuffix, maCNStr, seqStr)));

        repo.add(sv);
    }

    public void update(SinhVien sv) { repo.update(sv); }

    public void delete(Long maSV) { repo.delete(maSV); }

    public List<SinhVienDTO> findAll() {
        List<SinhVienDTO> list = repo.findAllSV();
        return list;
    }
}
