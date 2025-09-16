package com.example.university.web.controller;

import com.example.university.model.HocPhan;
import com.example.university.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hocPhan")
public class HocPhanApiController {

    @Autowired
    private HocPhanService hocPhanService;

    @GetMapping
    public List<HocPhan> getAll() {
        return hocPhanService.getAllHocPhan();
    }

    @GetMapping("/{maHP}")
    public HocPhan getOne(@PathVariable int maHP) {
        return hocPhanService.getHocPhanById(maHP);
    }

    @PostMapping("/save")
    public HocPhan save(@RequestBody HocPhan hocPhan) {
        if (hocPhan.getMaHP() == null) {
            hocPhanService.addHocPhan(hocPhan);
        } else {
            hocPhanService.updateHocPhan(hocPhan);
        }
        return hocPhan;
    }

    @DeleteMapping("/delete/{maHP}")
    public void delete(@PathVariable int maHP) {
        hocPhanService.deleteHocPhan(maHP);
    }
}
