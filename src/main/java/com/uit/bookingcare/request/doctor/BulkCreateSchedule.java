package com.uit.bookingcare.request.doctor;

import com.uit.bookingcare.dto.schedule.ScheduleDto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BulkCreateSchedule {
    private Long doctorId;
    private LocalDate formatedDate;
    private ScheduleDto arrSchedule;

}
