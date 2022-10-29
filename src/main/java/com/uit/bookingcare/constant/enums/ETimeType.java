package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum ETimeType {
    T1("Lịch hẹn mới"),
    CONFIRMED("Đã xác nhận"),
    DONE("Đã khám xong"),
    CACNEL("Đã hủy");
    @Getter
    private String value;

    ETimeType(String value) {
        this.value = value;
    }
}
