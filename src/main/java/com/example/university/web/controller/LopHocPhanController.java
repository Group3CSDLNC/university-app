package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.HocPhan;
import com.example.university.model.LopHocPhan;
import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.HocPhanService;
import com.example.university.service.LopHocPhanService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lopHocPhan")
public class LopHocPhanController {

    @Autowired
    private LopHocPhanService lopHocPhanService;
    @Autowired
    private ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;
    @Autowired
    private HocPhanService hocPhanService;

    @Autowired
    private SessionService sessionService;

//    @GetMapping("/lopHocPhan_list")
//    public String listLopHocPhan(Model model,
//                                 @RequestParam(value = "hocKy", required = false) Integer hocKy,
//                                 @RequestParam(value = "namHoc", required = false) String namHoc,
//                                 @RequestParam(value = "maCTDT", required = false) Long maCTDT,
//                                 @RequestParam(value = "maHP", required = false) Long maHP,
//                                 @RequestParam(value = "maGV", required = false) Integer maGV) {
//
//        if (!sessionService.isLoggedIn()) {
//            return "redirect:/login";
//        }
//
//        // Lấy danh sách lớp học phần
//        List<Map<String, Object>> lopHocPhans = lopHocPhanService.list(hocKy, namHoc, maCTDT, maGV);
//        model.addAttribute("lopHocPhans", lopHocPhans);
//
//        // Danh sách năm học
//        List<String> years = new ArrayList<>();
//        for (int start = 2020; start <= 2028; start++) {
//            years.add(start + "-" + (start + 1));
//        }
//        model.addAttribute("years", years);
//
//        // Danh sách CTDT
//        List<ChuongTrinhDaoTao> ctdts = chuongTrinhDaoTaoService.getAll("");
//        Map<Long, String> ctdtMap = ctdts.stream()
//                .collect(Collectors.toMap(ChuongTrinhDaoTao::getMaCTDT, ChuongTrinhDaoTao::getTenCTDT));
//        model.addAttribute("ctdts", ctdts);
//        model.addAttribute("ctdtMap", ctdtMap);
//
//        // Danh sách Học phần (ăn theo CTDT nếu có)
//        List<HocPhan> hocphans;
//        if (maCTDT != null) {
//            hocphans = hocPhanService.getByMaCTDT(maCTDT);
//        } else {
//            hocphans = hocPhanService.getAllHocPhan();
//        }
//        Map<Long, String> hocphanMap = hocphans.stream()
//                .collect(Collectors.toMap(HocPhan::getMaHP, HocPhan::getTenHP));
//        model.addAttribute("hocphans", hocphans);
//        model.addAttribute("hocphanMap", hocphanMap);
//        model.addAttribute("lopHocPhans", lopHocPhans);
//
//        // Giữ lại filter
//        model.addAttribute("hocKy", hocKy);
//        model.addAttribute("namHoc", years);
//        model.addAttribute("maCTDT", maCTDT);
//        model.addAttribute("maHP", maHP);
//        model.addAttribute("maGV", maGV);
//        model.addAttribute("sessionService", sessionService);
//
//        return "lopHocPhan/lopHocPhan_list";
//    }
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

        return "lopHocPhan/lopHocPhan_list";
    }

}
