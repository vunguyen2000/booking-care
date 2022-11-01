package com.uit.bookingcare.dto.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.dto.BaseDto;
import com.uit.bookingcare.dto.patient.GenderDataDto;
import com.uit.bookingcare.dto.patient.PatientDataDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorPatientBookingDto extends BaseDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private EStatus statusId;
    private LocalDate date;
    private ETimeType timeType;
    private TimeTypeDataDto timeTypeDataPatient;
    private GenderDataDto genderDataDto;
    private PatientDataDto patientData;
    private String token;

}
