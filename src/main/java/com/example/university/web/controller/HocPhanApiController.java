package com.example.university.web.controller;

import com.example.university.model.HocPhan;
import com.example.university.service.HocPhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/hocPhan")
public class HocPhanApiController {

    @Autowired
    private HocPhanService hocPhanService;

    // Lấy danh sách
    @GetMapping
    public List<HocPhan> getAll() {
        return hocPhanService.getAllHocPhan();
    }

    // Lấy theo id
    @GetMapping("/{maHP}")
    public HocPhan getById(@PathVariable int maHP) {
        return hocPhanService.getHocPhanById(maHP);
    }

    // Thêm / Cập nhật
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody HocPhan hocPhan) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (hocPhan.getMaHP() != 0) {
                hocPhanService.updateHocPhan(hocPhan);
                response.put("message", "Cập nhật thành công");
            } else {
                hocPhanService.addHocPhan(hocPhan);
                response.put("message", "Thêm mới thành công");
            }
            response.put("hocPhan", hocPhan);
            return ResponseEntity.ok(response);
        } catch (DataAccessException ex) {
            Throwable root = ex.getRootCause();
            String msg = (root != null) ? root.getMessage() : ex.getMessage();
            response.put("message", msg != null ? msg : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (RuntimeException ex) {
            response.put("message", ex.getMessage() != null ? ex.getMessage() : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Xóa
    @DeleteMapping("/delete/{maHP}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int maHP) {
        Map<String, String> response = new HashMap<>();
        try {
            hocPhanService.deleteHocPhan(maHP);
            response.put("message", "Xóa thành công");
            return ResponseEntity.ok(response);
        } catch (DataAccessException ex) {
            Throwable root = ex.getRootCause();
            String msg = (root != null) ? root.getMessage() : ex.getMessage();
            if(msg != null && msg.contains("Không thể xóa")) {
                msg = msg.substring(msg.indexOf("Không thể xóa"));
            }
            response.put("message", msg != null ? msg : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (RuntimeException ex) {
            response.put("message", ex.getMessage() != null ? ex.getMessage() : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

