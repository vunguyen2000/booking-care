package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPrice {
    PRI1(10,"200000"),
    PRI2(15,"250000"),
    PRI3(20,"300000"),
    PRI4(25,"350000"),
    PRI5(30,"400000"),
    PRI6(35,"450000"),
    PRI7(40,"500000");

    @Getter
    private final Integer valueEn;

    @Getter
    private final String valueVi;

    EPrice(Integer valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
