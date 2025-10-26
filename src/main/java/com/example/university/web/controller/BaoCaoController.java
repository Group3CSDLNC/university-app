package com.example.university.web.controller;

import com.example.university.service.BaoCaoService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/baocao")
public class BaoCaoController {

    @Autowired
    private BaoCaoService service;
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private SessionService sessionService;

    public BaoCaoController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private void checkLogin() {
        if (!sessionService.isLoggedIn()) {
            throw new RuntimeException("Chua dang nhap!");
        }
    }

    @GetMapping("/bangdiem/{maSV}/{maCTDT}")
    public List<Map<String,Object>> bangDiem(@PathVariable Long maSV, @PathVariable Long maCTDT) {
//        checkLogin();
        return service.bangDiem(maSV, maCTDT);
    }

    @GetMapping("/chua-hoanthanh")
    public List<Map<String,Object>> chuaHoanThanh(@RequestParam String maCTDT,@RequestParam String maSV) {
        Long maSVLong = (maSV != null && !maSV.isEmpty()) ? Long.valueOf(maSV) : null;
        Long maCTDTLong = (maCTDT != null && !maCTDT.isEmpty()) ? Long.valueOf(maCTDT) : null;
        return service.chuaHoanThanh(maSVLong,maCTDTLong);
    }

    @GetMapping("/siso")
    public List<Map<String,Object>> baoCaoSiSo() {
        checkLogin();
        return service.baoCaoSiSo();
    }

    @GetMapping("/ctdt-list")
    public List<Map<String,Object>> getCTDTList() {
        checkLogin();
        return jdbc.queryForList("SELECT MaCTDT AS maCTDT, TenCTDT AS tenCTDT FROM ChuongTrinhDaoTao");
    }
}
