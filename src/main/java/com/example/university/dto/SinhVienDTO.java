package com.example.university.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SinhVienDTO {
    private Long maSV;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private Long maCN;
    private Integer tinhTrang;
    private String email;
    private Integer namHoc;
    private String tenCN;
}
