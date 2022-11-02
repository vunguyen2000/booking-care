package com.uit.bookingcare.dto.schedule;

import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ArrSchedule extends BaseDto {
   private Long currentNumber;
   private Long maxNumber;
   private LocalDate date;
   private ETimeType timeType;
   private Long doctorId;


}
