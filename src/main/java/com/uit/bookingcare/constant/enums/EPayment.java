package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPayment {

    PAY1("Cash","Tiền mặt"),
    PAY2("Credit card","Thẻ ATM"),
    PAY3("All payment method","Tất cả");

    @Getter
    private final String valueEn;

    @Getter
    private final String valueVi;

    EPayment(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
