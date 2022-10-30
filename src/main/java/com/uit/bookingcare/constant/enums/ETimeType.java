package com.uit.bookingcare.constant.enums;

import lombok.Getter;

@Getter
public enum ETimeType {
    T1("8:00 AM - 9:00 AM", "8:00 - 9:00"),
    T2("9:00 AM - 10:00 AM", "9:00 - 10:00"),
    T3("10:00 AM - 11:00 AM", "10:00 - 11:00"),
    T4("11:00 AM - 0:00 PM", "11:00 - 12:00"),
    T5("1:00 PM - 2:00 PM", "13:00 - 14:00"),
    T6("2:00 PM - 3:00 PM", "14:00 - 15:00"),
    T7( "3:00 PM - 4:00 PM", "15:00 - 16:00"),
    T8("4:00 PM - 5:00 PM", "16:00 - 17:00");


    @Getter
    private final String valueEn;

    @Getter
    private final String valueVi;

    ETimeType(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }

}
