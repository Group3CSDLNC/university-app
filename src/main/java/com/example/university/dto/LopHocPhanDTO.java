package com.example.university.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LopHocPhanDTO {
    private String maLHP;
    private Integer maHP;
    private String tenHP;
    private Integer soTinChi;
    private Integer tietLT;
    private Integer tietTH;
    private Integer siSoToiDa;
    private Integer soLuongDaDangKy;
    private Integer slotConLai;
    private String namHoc;
    private Integer hocKy;
    private String phongHoc;
    private String giangVienChinh;
    private String troGiang;
    private String tenChuyenNganh;
    private String tenChuongTrinhDaoTao;

    // Thêm mới
    private String ngayBatDau;   // yyyy-MM-dd
    private String ngayKetThuc;  // yyyy-MM-dd
    private Integer tietBatDau;
    private Integer thu;
    private Boolean isDangKy;
}
