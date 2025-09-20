package com.example.university.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module")
public class ModuleController {

    // Khi admin click vào menu "Chương trình đào tạo", frontend fetch tới /module/ctdt
    @GetMapping("/ctdt")
    public String loadCTDTModule() {
        return "chuongtrinhdaotao/chuongtrinhdaotao_module"; // chỉ load module container
    }
    @GetMapping("/hocphan")
    public String loadHocphanModule() {
        return "hocphan/hocphan_module"; // chỉ load module container
    }

    // Sau này bạn có thể thêm các module khác:
    // @GetMapping("/monhoc")
    // public String loadMonHocModule() { return "monhoc/monhoc_list"; }
}
