package com.example.university.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser {
    private Integer maTK;
    private String tenDangNhap;
    private String loaiTaiKhoan;
    private Long maSV;
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
}

