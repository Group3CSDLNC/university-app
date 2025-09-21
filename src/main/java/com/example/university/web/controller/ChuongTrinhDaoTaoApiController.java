package com.example.university.web.controller;

import com.example.university.model.ChuongTrinhDaoTao;
import com.example.university.service.ChuongTrinhDaoTaoService;
import com.example.university.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ctdt")
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

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<ChuongTrinhDaoTao>> searchCTDT(@RequestParam String keyword) {
        try {
            List<ChuongTrinhDaoTao> ctdtList = service.searchCTDT(keyword);
            return ResponseEntity.ok(ctdtList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCTDT(@RequestBody ChuongTrinhDaoTao ctdt) {
        try {
            checkLogin();
            service.addChuongTrinh(ctdt.getTenCTDT(), ctdt.getTongTinChi(),
                    ctdt.getMaCN(), ctdt.getNamHoc());
            return ResponseEntity.ok("Thêm CTDT thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCTDT(@RequestBody ChuongTrinhDaoTao ctdt) {
        try {
            checkLogin();
            service.updateChuongTrinh(ctdt.getMaCTDT(), ctdt.getTenCTDT(), ctdt.getTongTinChi(),
                    ctdt.getMaCN(), ctdt.getNamHoc());
            return ResponseEntity.ok("Cập nhật CTDT thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{maCTDT}")
    public ResponseEntity<?> deleteCTDT(@PathVariable Integer maCTDT) {
        try {
            checkLogin();
            service.deleteChuongTrinh(maCTDT);
            return ResponseEntity.ok("Xóa CTDT thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignNhanVienQuanLy(@RequestParam Integer maCTDT,
                                                  @RequestParam Integer maNV) {
        try {
            checkLogin();
            service.assignNhanVienQuanLy(maCTDT, maNV);
            return ResponseEntity.ok("Gán nhân viên quản lý thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{maCTDT}")
    public ResponseEntity<?> getCTDT(@PathVariable Integer maCTDT) {
        try {
            checkLogin();
            ChuongTrinhDaoTao ctdt = service.listCTDT(null, null, null)
                    .stream()
                    .filter(c -> c.getMaCTDT().equals(maCTDT))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("CTDT không tồn tại!"));
            return ResponseEntity.ok(ctdt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{maCTDT}/quan-ly")
    public ResponseEntity<?> getQuanLyCTDT(@PathVariable Integer maCTDT) {
        try {
            Map<String, Object> result = service.getQuanLyCTDT(maCTDT);
            return ResponseEntity.ok(result);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy nhân viên quản lý cho CTDT có mã " + maCTDT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi: " + e.getMessage());
        }
    }

//    @GetMapping("/{id}")
//    public ChuongTrinhDaoTao getById(@PathVariable int id) {
//        checkLogin();
//        return service.getClass(id);
//    }

//    @PostMapping("/save")
//    public ResponseEntity<Map<String, Object>> save(@RequestBody ChuongTrinhDaoTao ctdt) {
//        checkLogin();
//        Map<String, Object> response = new HashMap<>();
//        try {
//            if (ctdt.getMaCTDT() != null) {
//                service.update(ctdt);
//                response.put("message", "Cập nhật thành công");
//            } else {
//                int newId = service.add(ctdt);
//                ctdt.setMaCTDT(newId);
//                response.put("message", "Thêm mới thành công");
//            }
//            response.put("ctdt", ctdt);
//            return ResponseEntity.ok(response);
//        } catch (DataAccessException ex) {
//            Throwable root = ex.getRootCause();
//            String msg = (root != null) ? root.getMessage() : ex.getMessage();
//            response.put("message", msg != null ? msg : "Lỗi không xác định");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        } catch (RuntimeException ex) {
//            response.put("message", ex.getMessage() != null ? ex.getMessage() : "Lỗi không xác định");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
//        checkLogin();
//        Map<String, String> response = new HashMap<>();
//        try {
//            service.delete(id);
//            response.put("message", "Xóa thành công");
//            return ResponseEntity.ok(response);
//        } catch (DataAccessException ex) {
//            Throwable root = ex.getRootCause();
//            String msg = (root != null) ? root.getMessage() : ex.getMessage();
//            if(msg != null && msg.contains("Khong the xoa")) {
//                msg = msg.substring(msg.indexOf("Khong the xoa"));
//            }
//            response.put("message", msg != null ? msg : "Lỗi không xác định");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        } catch (RuntimeException ex) {
//            response.put("message", ex.getMessage() != null ? ex.getMessage() : "Lỗi không xác định");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }
}

