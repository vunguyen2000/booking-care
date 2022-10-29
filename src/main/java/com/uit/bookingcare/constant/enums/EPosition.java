package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPosition {
    NONE("Bác sĩ"),
    MASTER("Thạc sĩ"),
    DOCTOR("Tiến sĩ"),
    ASSOCICATE_PROFESSOR("Phó giáo sư"),
    PROFESSOR("Giáo sư");

    @Getter
    private String value;

    EPosition(String value) {
        this.value = value;
    }
}