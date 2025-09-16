package com.example.university.web.controller;

import com.example.university.model.HocPhan;
import com.example.university.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hocPhan")
public class HocPhanController {

    @Autowired
    private HocPhanService hocPhanService;

    // Danh sách
    @GetMapping
    public String listHocPhan(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<HocPhan> hocphans;
        if (keyword != null && !keyword.trim().isEmpty()) {
            hocphans = hocPhanService.searchHocPhan(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            hocphans = hocPhanService.getAllHocPhan();
        }

        // Tạo map: maHP -> tenHP
        Map<Integer, String> hocphanMap = hocphans.stream()
                .collect(Collectors.toMap(HocPhan::getMaHP, HocPhan::getTenHP));
        model.addAttribute("hocphans", hocphans);
        model.addAttribute("hocphanMap", hocphanMap);

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