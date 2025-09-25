package com.example.university.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LichHoc {
    private Integer maLichHoc;
    private Long maLHP;
    private Integer tietBatDau;
    private Integer maPH;
    private Integer soTiet;
    private Integer thuTrongTuan;
    private Date ngayBatDau;  // Ngày bắt đầu học
    private Date ngayKetThuc; // Ngày kết thúc học
}