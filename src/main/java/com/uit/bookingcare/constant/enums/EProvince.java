package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EProvince {
    PRO1("Ha Noi","Hà Nội"),
    PRO2("Ho Chi Minh","Hồ Chí Minh"),
    PRO3("Da Nang","Đà Nẵng"),
    PRO4("Can Tho","Cần Thơ"),
    PRO5("Binh Duong","Bình Dương"),
    PRO6("Dong Nai","Đồng Nai"),
    PRO8("Hue","Thừa Thiên Huế"),
    PRO7("Quang Ninh","Quảng Ninh"),
    PRO9("Quang Binh","Quảng Bình"),
    PRO10("Khanh Hoa","Khánh Hòa");
    @Getter
    private final String valueEn;

    @Getter
    private final String valueVi;

    EProvince(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
