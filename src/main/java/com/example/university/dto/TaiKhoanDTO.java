package com.example.university.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaiKhoanDTO {
    private int maTK;
    private String tenDangNhap;
    private String loaiTaiKhoan;
    private Integer maSV;
    private String tenSinhVien;
    private String emailSinhVien;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String chuyenNganh;
    private Integer maNV;
    private String tenNhanVien;
    private String emailNhanVien;
    private String hocVi;
    private String vaiTro;
}
