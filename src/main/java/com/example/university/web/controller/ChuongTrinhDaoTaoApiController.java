package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chuongTrinhDaoTao")
public class ChuongTrinhDaoTaoApiController {

    @Autowired
    private ChuongTrinhDaoTaoService service;

    @Autowired
    private SessionService sessionService;

    private void checkLogin() {
        if (!sessionService.isLoggedIn()) {
            throw new RuntimeException("Chua dang nhap!");
        }
    }

    @GetMapping
    @ResponseBody
    public List<ChuongTrinhDaoTao> listCTDT(@RequestParam(value="keyword", required=false) String keyword) {
        checkLogin();
        if(keyword != null && !keyword.isEmpty()) {
            return service.search(keyword);
        } else {
            return service.getAll("");
        }
    }


    @GetMapping("/{id}")
    public ChuongTrinhDaoTao getById(@PathVariable int id) {
        checkLogin();
        return service.getById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody ChuongTrinhDaoTao ctdt) {
        checkLogin();
        Map<String, Object> response = new HashMap<>();
        try {
            if (ctdt.getMaCTDT() != null) {
                service.update(ctdt);
                response.put("message", "Cập nhật thành công");
            } else {
                int newId = service.add(ctdt);
                ctdt.setMaCTDT(newId);
                response.put("message", "Thêm mới thành công");
            }
            response.put("ctdt", ctdt);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        checkLogin();
        Map<String, String> response = new HashMap<>();
        try {
            service.delete(id);
            response.put("message", "Xóa thành công");
            return ResponseEntity.ok(response);
        } catch (DataAccessException ex) {
            Throwable root = ex.getRootCause();
            String msg = (root != null) ? root.getMessage() : ex.getMessage();
            if(msg != null && msg.contains("Khong the xoa")) {
                msg = msg.substring(msg.indexOf("Khong the xoa"));
            }
            response.put("message", msg != null ? msg : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (RuntimeException ex) {
            response.put("message", ex.getMessage() != null ? ex.getMessage() : "Lỗi không xác định");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

