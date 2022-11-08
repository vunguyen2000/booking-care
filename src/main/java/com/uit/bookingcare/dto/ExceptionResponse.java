package com.uit.bookingcare.dto;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String message;
    private int status;

    private int errorCode;

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.errorCode = 2;
    }
}
