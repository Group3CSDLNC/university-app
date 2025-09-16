package com.example.university.service;

import com.example.university.model.HocPhan;
import com.example.university.repository.HocPhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HocPhanService {

    @Autowired
    private HocPhanRepository repo;

    public void addHocPhan(HocPhan hocPhan) {
        repo.insert(hocPhan);
    }

    public void updateHocPhan(HocPhan hocPhan) {
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

}
