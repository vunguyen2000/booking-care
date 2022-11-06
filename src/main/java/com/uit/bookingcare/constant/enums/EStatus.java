package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EStatus {
    S1("New","Lịch hẹn mới"),
    S2("Confirmed","Đã xác nhận"),
    S3("Done","Đã khám xong"),
    S4("Cancel","Đã hủy");

    @Getter
    private final String valueEn;

    @Getter
    private final String valueVi;

    EStatus(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
