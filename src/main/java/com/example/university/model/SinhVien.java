package com.example.university.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SinhVien {
    private Integer maSV;
    private String hoTen;
    private java.sql.Date ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private Integer maCN;
    private Integer tinhTrang;
    private String email;
    private Integer namHoc;
}