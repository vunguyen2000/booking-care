package com.uit.bookingcare.request.booking;

import com.uit.bookingcare.constant.enums.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostBookAppointment {
    private String email;
    private Long scheduleId;
    private EGender selectedGender;
    private String fullName;
    private String address;
    private Long patientId;
    private EStatus statusId;
    private String phonenumber;
}
