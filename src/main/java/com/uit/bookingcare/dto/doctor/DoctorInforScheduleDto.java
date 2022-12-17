package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.dto.BaseDto;
import com.uit.bookingcare.dto.schedule.ScheduleDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorInforScheduleDto extends BaseDto {

    private DetailDoctorDataDto doctor;
    private List<ScheduleDto> schedules;
}
