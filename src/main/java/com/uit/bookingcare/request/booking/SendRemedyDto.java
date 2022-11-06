package com.uit.bookingcare.request.booking;

import com.uit.bookingcare.constant.enums.EStatus;
import lombok.Data;

@Data
public class SendRemedyDto {
    private String email;
    private String token;
    private EStatus statusId;
    private String imgBase64;
}
