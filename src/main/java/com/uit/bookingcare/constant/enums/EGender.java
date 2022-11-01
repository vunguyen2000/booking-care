package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EGender {
    M("Male","Nam"),
    F("Female","Nữ"),
    O("Other","Khác");
    @Getter

    private final String valueEn;

    @Getter
    private final String valueVi;

    EGender(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
