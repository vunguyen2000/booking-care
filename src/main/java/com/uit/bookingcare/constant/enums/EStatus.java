package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EStatus {
    NEW("Lịch hẹn mới"),
    CONFIRMED("Đã xác nhận"),
    DONE("Đã khám xong"),
    CACNEL("Đã hủy");
    @Getter
    private String value;

    EStatus(String value) {
        this.value = value;
    }
}
