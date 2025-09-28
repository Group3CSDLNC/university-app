package com.example.university.service;

import com.example.university.dto.LuongGVDTO;
import com.example.university.model.NhanVien;
import com.example.university.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository repo;

    public List<NhanVien> getAll() { return repo.findAll(); }

    public List<NhanVien> findByVaiTro(List<String> vaiTros) { return repo.findByVaiTro(vaiTros); }

    public NhanVien getById(int maNV) { return repo.findById(maNV); }

    public void add(NhanVien nv) { repo.add(nv); }

    public void update(NhanVien nv) { repo.update(nv); }

    public void delete(int maNV) { repo.delete(maNV); }

    public List<LuongGVDTO> danhSachLuongGV(int thang, int nam) {
        return repo.danhSachLuongGV(thang, nam);
    }

    public List<Map<String, Object>> getLuong(Integer maNV, int thang, int nam) {
        return repo.tinhLuong(maNV, thang, nam);
    }
    public List<NhanVien> getGiangVienChinhByLHP(Long maLHP) {
        return repo.getGiangVienChinhByLHP(maLHP);
    }

    public List<NhanVien> getTroGiangByLHP(Long maLHP) {
        return repo.getTroGiangByLHP(maLHP);
    }
}
