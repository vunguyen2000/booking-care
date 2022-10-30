package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDataDto {

    private String firstName;
    private String lastName;

    public DoctorDataDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
