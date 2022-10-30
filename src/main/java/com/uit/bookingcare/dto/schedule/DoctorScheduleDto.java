package com.uit.bookingcare.dto.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.dto.BaseDto;
import com.uit.bookingcare.dto.doctor.DoctorDataDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorScheduleDto extends BaseDto {
    private Long id;

    private Long currentNumber;
    private Long maxNumber;
    private LocalDate date;
    private ETimeType timeType;
    private Long doctorId;
    private TimeTypeDataDto timeTypeData;
    private DoctorDataDto doctorData;
}
