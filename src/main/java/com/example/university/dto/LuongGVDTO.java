package com.example.university.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LuongGVDTO {
    private int maNV;
    private String hoTen;
    private int tongGioDay;
    private BigDecimal luong;
}