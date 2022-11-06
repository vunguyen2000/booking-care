package com.uit.bookingcare.request.booking;

import com.uit.bookingcare.constant.enums.EStatus;
import lombok.Data;

@Data
public class VerifyBookAppointmentDto {
    private String token;
    private EStatus statusId;
}
