package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.ChuyenNganh;
import com.example.university.model.HocPhan;
import com.example.university.model.SinhVien;
import com.example.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private HocPhanService hocPhanService;
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

    @GetMapping("/nhap")
    public String nhapDiem(
            @RequestParam(required = false) Long maLHP,
            @RequestParam(required = false) Long maSV,
            @RequestParam(required = false) String keyword,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);
        List<Map<String, Object>> dsHocVien = ketQuaService.getDanhSachHocVien(maLHP, maSV, keyword);

        // Tạo Map MaSV -> HoTen để filter / lookup
        Map<Long, String> hocVienMap = dsHocVien.stream()
                .collect(Collectors.toMap(
                        sv -> ((Number) sv.get("MaSV")).longValue(),
                        sv -> (String) sv.get("HoTen")
                ));
        model.addAttribute("HocVienMap", hocVienMap);
        model.addAttribute("dsHocVien", dsHocVien);
        model.addAttribute("maLHP", maLHP);
        model.addAttribute("maSV", maSV);
        model.addAttribute("keyword", keyword);
        return "diemthi/nhapdiem";
    }

    @GetMapping("/lopHocPhan_list")
    public String viewLopHocPhan(
            @RequestParam(required = false) Integer hocKy,
            @RequestParam(required = false) String namHoc,
            @RequestParam(required = false) Long maCTDT,
            @RequestParam(required = false) Integer maGV,
            @RequestParam(required = false) Long maHP,
            Model model) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        model.addAttribute("sessionService", sessionService);


        // 1️⃣ Lấy danh sách lớp học phần theo filter
        List<Map<String, Object>> danhSach = lopHocPhanService.getLopHocPhan(hocKy, namHoc, maCTDT, maGV, maHP);
        model.addAttribute("lophocphanList", danhSach);

        // 2️⃣ Lấy danh sách CTDT cho dropdown
        List<ChuongTrinhDaoTao> ctdts = chuongTrinhDaoTaoService.getAll("");
        model.addAttribute("ctdtList", ctdts);

        // 3️⃣ Danh sách năm học (từ năm hiện tại đến 5 năm sau)
        int currentYear = LocalDate.now().getYear();
        List<String> years = new ArrayList<>();
        for (int start = currentYear; start <= currentYear + 5; start++) {
            years.add(start + "-" + (start + 1));
        }
        model.addAttribute("years", years);

        // 4️⃣ Danh sách học phần (ăn theo CTDT nếu có)
        List<HocPhan> hocphans;
        if (maCTDT != null) {
            hocphans = hocPhanService.getByMaCTDT(maCTDT);
        } else {
            hocphans = hocPhanService.getAllHocPhan();
        }
        model.addAttribute("hocphanList", hocphans);

        // 5️⃣ Giữ trạng thái filter trên FE
        model.addAttribute("hocKy", hocKy);
        model.addAttribute("namHoc", namHoc);
        model.addAttribute("maCTDT", maCTDT);
        model.addAttribute("maHP", maHP);

        return "diemthi/lopHocPhan_list";
    }
}
