package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.dto.clinics.ClinicSearchDto;
import com.uit.bookingcare.dto.specialty.SpecialtySearchDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDto {
    private List<DoctorSearchDto> doctors;
    private List<ClinicSearchDto> clinics;
    private List<SpecialtySearchDto> specialties;

}
