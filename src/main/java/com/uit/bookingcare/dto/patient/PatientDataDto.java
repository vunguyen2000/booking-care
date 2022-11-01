package com.uit.bookingcare.dto.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDataDto {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private GenderDataDto genderData;
    private EGender gender;
    public PatientDataDto(String email,String firstName, String lastName,String address, EGender gender,GenderDataDto genderData) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.genderData = genderData;
    }
}
