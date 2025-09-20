package com.example.university.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser {
    private Integer maTK;
    private String tenDangNhap;
    private String loaiTaiKhoan;
    private Integer maSV;
    private Integer maNV;

    public boolean isLoggedIn() {
        return maTK != null;
    }

    public void clear() {
        this.maTK = null;
        this.tenDangNhap = null;
        this.loaiTaiKhoan = null;
        this.maSV = null;
        this.maNV = null;
    }

    // getter & setter
    public Integer getMaTK() { return maTK; }
    public void setMaTK(Integer maTK) { this.maTK = maTK; }

    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }

    public String getLoaiTaiKhoan() { return loaiTaiKhoan; }
    public void setLoaiTaiKhoan(String loaiTaiKhoan) { this.loaiTaiKhoan = loaiTaiKhoan; }

    public Integer getMaSV() { return maSV; }
    public void setMaSV(Integer maSV) { this.maSV = maSV; }

    public Integer getMaNV() { return maNV; }
    public void setMaNV(Integer maNV) { this.maNV = maNV; }
}

