package com.example.university.service;

import com.example.university.dto.LuongGVDTO;
import com.example.university.dto.NhanVienDTO;
import com.example.university.model.NhanVien;
import com.example.university.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository repo;

    public List<NhanVien> getAll() { return repo.findAll(); }

    public List<NhanVien> findByVaiTro(List<String> vaiTros) { return repo.findByVaiTro(vaiTros); }

    public NhanVienDTO getById(int maNV) { return repo.findById(maNV); }

    public void add(NhanVienDTO nv) { repo.add(nv); }

    public void update(NhanVienDTO nv) { repo.update(nv); }

    public void delete(int maNV) { repo.delete(maNV); }

    public List<LuongGVDTO> danhSachLuongGV(int thang, int nam) {
        return repo.danhSachLuongGV(thang, nam);
    }
    public List<NhanVien> getGiangVienChinhByLHP(Long maLHP) {
        return repo.getGiangVienChinhByLHP(maLHP);
    }

    public List<NhanVien> getTroGiangByLHP(Long maLHP) {
        return repo.getTroGiangByLHP(maLHP);
    }
}
