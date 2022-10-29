package com.uit.bookingcare.constant.enums;

import lombok.Getter;

public enum EProvince {
    HANOI("Hà Nội"),
    HOCHIMINH("Hồ Chí Minh"),
    DANANG("Đà Nẵng"),
    CANTHO("Cần Thơ"),
    BINHDUONG("Bình Dương"),
    DONGNAI("Đồng Nai"),
    HUE("Huế"),
    QUANGNINH("Quảng Ninh"),
    QUANGBINH("Quảng Bình"),
    THANHHOA("Thanh Hóa");
    @Getter
    private String value;

    EProvince(String value) {
        this.value = value;
    }
}
