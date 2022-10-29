package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EPayment {

    CASH("Tiền mặt"),
    CREDIT_CARD("Thẻ ATM"),
    ALL_PAYMENT_METHOD("Tất cả");

    @Getter
    private String value;

    EPayment(String value) {
        this.value = value;
    }
}
