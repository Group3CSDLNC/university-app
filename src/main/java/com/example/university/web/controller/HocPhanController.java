package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.model.HocPhan;
import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.HocPhanService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hocPhan")
public class HocPhanController {

    @Autowired
    private HocPhanService hocPhanService;

    @Autowired
    private ChuongTrinhDaoTaoService chuongTrinhDaoTaoService;

    @Autowired
    private SessionService sessionService;
    // Danh sách
    @GetMapping("/hocphan_list")
    public String listHocPhan(Model model,
                              @RequestParam(value = "keyword", required = false) String keyword) {
        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        List<HocPhan> hocphans;
        if (keyword != null && !keyword.trim().isEmpty()) {
            hocphans = hocPhanService.searchHocPhan(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            hocphans = hocPhanService.getAllHocPhan();
        }

        Map<Long, String> hocphanMap = hocphans.stream()
                .collect(Collectors.toMap(HocPhan::getMaHP, HocPhan::getTenHP));

        List<ChuongTrinhDaoTao> ctdts = chuongTrinhDaoTaoService.getAll("");

        Map<Long, String> ctdtMap = ctdts.stream()
                .collect(Collectors.toMap(ChuongTrinhDaoTao::getMaCTDT, ChuongTrinhDaoTao::getTenCTDT));

        model.addAttribute("hocphans", hocphans);
        model.addAttribute("hocphanMap", hocphanMap);
        model.addAttribute("ctdts", ctdts);     // cho dropdown thêm/sửa
        model.addAttribute("ctdtMap", ctdtMap); // cho hiển thị list
        model.addAttribute("sessionService", sessionService);

        return "hocphan/hocphan_list";
    }

    // Lấy 1 học phần theo id (dùng cho popup sửa)
    @GetMapping("/{id}")
    @ResponseBody
    public HocPhan getHocPhan(@PathVariable("id") int id) {
        return hocPhanService.getHocPhanById(id);
    }

    // Lưu hoặc cập nhật
    @PostMapping("/save")
    @ResponseBody
    public HocPhan saveHocPhan(@RequestBody HocPhan hp) {
        if (hp.getMaHP() > 0) {
            hocPhanService.updateHocPhan(hp);
        } else {
            hocPhanService.addHocPhan(hp);
        }
        return hp;
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteHocPhan(@PathVariable("id") int id) {
        try {
            hocPhanService.deleteHocPhan(id);
            return "Xóa thành công";
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
    }
}