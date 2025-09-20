package com.example.university.service;

import com.example.university.repository.BaoCaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaoCaoService {

    @Autowired
    private BaoCaoRepository repo;

    public List<Map<String,Object>> bangDiem(int maSV, int maCTDT) {
        return repo.getBangDiem(maSV, maCTDT);
    }

    public List<Map<String,Object>> chuaHoanThanh(int maCTDT) {
        return repo.getChuaHoanThanh(maCTDT);
    }

    public List<Map<String,Object>> baoCaoSiSo() {
        return repo.getBaoCaoSiSo();
    }

}
