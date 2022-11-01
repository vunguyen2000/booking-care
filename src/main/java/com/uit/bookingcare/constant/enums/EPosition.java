package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPosition {
    P0("None","Bác sĩ"),
    P1("Master","Thạc sĩ"),
    P2("Doctor","Tiến sĩ"),
    P3("Asocicate Professor","Phó giáo sư"),
    PROFESSOR("Professor","Giáo sư");

    @Getter
    private final String valueEn;

    @Getter
    private final String valueVi;

    EPosition(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}