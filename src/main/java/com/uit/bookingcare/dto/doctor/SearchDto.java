package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.constant.enums.ERoleType;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.clinics.ClinicSearchDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto {
    private List<DoctorSearchDto> doctors;
    private List<ClinicSearchDto> clinics;

}
