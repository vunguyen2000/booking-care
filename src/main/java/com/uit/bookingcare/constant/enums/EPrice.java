package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPrice {
    PRI_1(10,"200000"),
    PRI_2(15,"250000"),
    PRI_3(20,"300000"),
    PRI_4(25,"350000"),
    PRI_5(30,"400000"),
    PRI_6(35,"450000"),
    PRI_7(40,"500000");

    @Getter
    private Integer number;

    @Getter
    private String value;

    EPrice(Integer number, String value) {
        this.number = number;
        this.value = value;
    }
}
