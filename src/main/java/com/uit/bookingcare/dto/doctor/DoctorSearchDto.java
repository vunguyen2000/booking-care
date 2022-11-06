package com.uit.bookingcare.dto.doctor;

import com.uit.bookingcare.constant.enums.ERoleType;
import lombok.Data;

@Data
public class DoctorSearchDto {
    private Long id;
    private String firstName;
    private String lastName;
    private ERoleType roleId;
}
