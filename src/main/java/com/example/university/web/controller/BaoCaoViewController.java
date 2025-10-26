package com.example.university.web.controller;

import com.example.university.service.BaoCaoService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bao-cao")
public class BaoCaoViewController {

    @Autowired
    private BaoCaoService baoCaoService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private JdbcTemplate jdbc;

    // Hiển thị trang chọn báo cáo + kết quả (GET với params)
    @GetMapping("/list")
    public String list(
            @RequestParam(required = false) String reportType,
            @RequestParam(required = false) String maSV,
            @RequestParam(required = false) String maCTDT,
            Model model) {

        if (!sessionService.isLoggedIn()) {
            return "redirect:/login";
        }
        Long maSVLong = (maSV != null && !maSV.isEmpty()) ? Long.valueOf(maSV) : null;
        Long maCTDTLong = (maCTDT != null && !maCTDT.isEmpty()) ? Long.valueOf(maCTDT) : null;
        // cung cấp session cho template (giống các controller khác)
        model.addAttribute("sessionService", sessionService);

        // Danh sách CTDT để chọn (từ DB)
        List<Map<String, Object>> ctdtList = jdbc.queryForList("SELECT MaCTDT AS maCTDT, TenCTDT AS tenCTDT FROM ChuongTrinhDaoTao");
        model.addAttribute("ctdtList", ctdtList);

        model.addAttribute("reportType", reportType);
        model.addAttribute("maCTDT", maCTDT);
        model.addAttribute("maSV", maSV);

        // Nếu không chọn loại báo cáo thì chỉ hiển thị form
        if (reportType == null || reportType.isEmpty()) {
            model.addAttribute("reportData", null);
            return "baocao/baocao_list";
        }

        // Lấy dữ liệu từ service tương ứng
        List<Map<String, Object>> reportData;
        String reportTitle = "Báo cáo";

        switch (reportType) {
            case "bangdiem":
                reportData = baoCaoService.bangDiem(maSVLong, maCTDTLong);

                reportTitle = "Bảng điểm sinh viên";
                break;
            case "chua-hoanthanh":
                reportData = baoCaoService.chuaHoanThanh(maSVLong, maCTDTLong);
                reportTitle = "Sinh viên chưa hoàn thành khóa";
                break;
            case "siso":
                reportData = baoCaoService.baoCaoSiSo();
                reportTitle = "Báo cáo sĩ số";
                break;
            default:
                reportData = null;
                break;
        }

        model.addAttribute("reportData", reportData);
        model.addAttribute("reportTitle", reportTitle);

        return "baocao/baocao_list";
    }
}
