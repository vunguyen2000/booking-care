package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.constant.enums.ERoleType;
import com.uit.bookingcare.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorInforDto extends BaseDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phonenumber;
    private String email;
    private EGender gender;
    private ERoleType roleId;
    private EPosition positionId;
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
