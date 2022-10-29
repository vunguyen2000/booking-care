package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EGender {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");
    @Getter
    private String value;

    EGender(String value){
        this.value = value;
    }
}
