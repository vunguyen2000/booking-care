package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EStatus {
    S1("S1"),
    S2("S2"),
    S3("S3"),
    S4("S4");
    @Getter
    private String value;

    EStatus(String value) {
        this.value = value;
    }
}
