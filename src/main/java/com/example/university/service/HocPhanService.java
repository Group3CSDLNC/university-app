package com.example.university.service;

import com.example.university.model.HocPhan;
import com.example.university.repository.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HocPhanService {

    @Autowired
    private HocPhanRepository repo;

    public void addHocPhan(HocPhan hocPhan) {
        // check trùng tên trong cùng CTDT
        if (existsByTenHPAndMaCTDT(hocPhan.getTenHP(), hocPhan.getMaCTDT(), null)) {
            throw new RuntimeException("Tên học phần đã tồn tại trong chương trình đào tạo này!");
        }

        // check tối đa 10 môn
        int count = countByMaCTDT(hocPhan.getMaCTDT());
        if (count >= 10) {
            throw new RuntimeException("Mỗi chương trình đào tạo chỉ được tối đa 10 học phần!");
        }

        repo.insert(hocPhan);
    }


    public void updateHocPhan(HocPhan hocPhan) {
        // check trùng tên trong cùng CTDT (trừ chính nó)
        if (existsByTenHPAndMaCTDT(hocPhan.getTenHP(), hocPhan.getMaCTDT(), hocPhan.getMaHP())) {
            throw new RuntimeException("Tên học phần đã tồn tại trong chương trình đào tạo này!");
        }

        repo.update(hocPhan);
    }

    public void deleteHocPhan(int maHP) {
        try {
            repo.delete(maHP);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Không thể xóa: học phần đang có lớp học phần tham chiếu!");
        }
    }


    public HocPhan getHocPhanById(int maHP) {
        return repo.getById(maHP);
    }

    public List<HocPhan> getAllHocPhan() {
        return repo.getAll();
    }

    public List<HocPhan> searchHocPhan(String keyword) {
        return repo.searchByKeyword(keyword);
    }

    public boolean existsByTenHPAndMaCTDT(String tenHP, Long maCTDT, Long excludeMaHP) {
        return repo.existsByTenHPAndMaCTDT(tenHP, maCTDT, excludeMaHP);
    }
    public int countByMaCTDT(Long maCTDT) {
        return repo.countByMaCTDT(maCTDT);
    }

    public List<HocPhan> getByMaCTDT(Long maCTDT) {
        return repo.getByMaCTDT(maCTDT);

    }
}
