package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.ChuyenNganh;
import com.example.university.model.SinhVien;
import com.example.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diem-thi")
public class KetQuaViewController {

    @Autowired
    private KetQuaService ketQuaService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;
    @Autowired
    private ChuyenNganhService chuyenNganhService;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private LopHocPhanService lopHocPhanService;
    // Hiển thị trang tổng kết
    @GetMapping("/list")
    public String tinhKetQuaTongKetView(
            @RequestParam(required = false) Long maSV,
            @RequestParam(required = false) Long maHP,
            @RequestParam(required = false) Long maCTDT,
            @RequestParam(required = false) Integer maCN,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        List<ChuongTrinhDaoTao> ctdts = chuongTrinhDaoTaoService.getAll("");
        model.addAttribute("ctdtList", ctdts);

        List<ChuyenNganh> chuyenNganhs = chuyenNganhService.getAll();
        model.addAttribute("chuyenNganhs", chuyenNganhs);

        List<SinhVien> sinhViens = sinhVienService.getAll();
        model.addAttribute("sinhViens", sinhViens);

        // Lấy kết quả từ service (luôn là List, thường chỉ có 1 bản ghi)
        List<Map<String, Object>> result = ketQuaService.tinhKetQuaHocTapTongKetV3(maSV, maCN);

        // Truyền dữ liệu vào model cho Thymeleaf
        model.addAttribute("tongKetList", result);
        model.addAttribute("maSV", maSV);
        model.addAttribute("maHP", maHP);
        model.addAttribute("maCTDT", maCTDT);

        return "diemthi/tinhKetQuaHocTapTongKet"; // file templates/tinhKetQuaHocTapTongKet.html
    }

    @GetMapping("/chitiet")
    public String chiTiet(
            @RequestParam (required = false) Long maSV ,
            @RequestParam(required = false) Long maHP,
            @RequestParam(required = false) Integer maCN,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);


        if (maSV == null ) {
            maSV = sessionService.getCurrentUser().getMaSV();
        }

        // Lấy kết quả từ service (luôn là List, thường chỉ có 1 bản ghi)
        List<Map<String, Object>> result = ketQuaService.xemKetQuaHocTap(maSV, maHP, maCN);
        List<Map<String, Object>> resultAll = ketQuaService.tinhKetQuaHocTapTongKetV3(maSV, maCN);


        Map<String, List<Map<String, Object>>> grouped = result.stream()
                .collect(Collectors.groupingBy(
                        row -> "Năm học: "+row.get("NamHoc") + " - Học Kỳ: " + row.get("HocKy"),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        model.addAttribute("groupedTongKet", grouped);
        if (!resultAll.isEmpty()) {
            model.addAttribute("firstRow", result.get(0));
        }
        if (!result.isEmpty()) {
            model.addAttribute("firstRowAll", resultAll.get(0));
        }
        return "diemthi/chitiet";
    }

    @GetMapping("/list/nhap")
    public String nhapDiem(
            @RequestParam(required = false) Integer maGV,
            @RequestParam(required = false) Long maHP,
            @RequestParam(required = false) Long maCTDT,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        List<Map<String, Object>> danhSach = lopHocPhanService.getLopHocPhan(null, null, maCTDT, maGV, maHP);
        model.addAttribute("lophocphanList", danhSach);


        return "diemthi/nhapdiem";
    }
}
