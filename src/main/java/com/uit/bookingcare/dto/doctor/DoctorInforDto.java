package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorInforDto {
    private Long id;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorInforDto doctorInforDto = (DoctorInforDto) o;
        return Objects.equals(id, doctorInforDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
