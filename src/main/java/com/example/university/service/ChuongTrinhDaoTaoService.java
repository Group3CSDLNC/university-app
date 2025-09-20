package com.example.university.service;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.repository.ChuongTrinhDaoTaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChuongTrinhDaoTaoService {

    @Autowired
    private ChuongTrinhDaoTaoRepository repository;

    @Autowired
    private SessionService sessionService;

    public List<ChuongTrinhDaoTao> getAll(String keyWord) {
        return repository.getAll(keyWord);
    }

    public ChuongTrinhDaoTao getById(int id) {
        return repository.getById(id);
    }

    public int add(ChuongTrinhDaoTao ctdt) {
        return repository.insert(ctdt);
    }

    public void update(ChuongTrinhDaoTao ctdt) {
        repository.update(ctdt);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public List<ChuongTrinhDaoTao> search(String keyword) {
        Integer maNV = sessionService.isLoggedIn() ? sessionService.getCurrentUser().getMaNV() : null;
        return repository.search(keyword, maNV);
    }
}


