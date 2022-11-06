package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.constant.enums.ERoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailDoctorDataDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phonenumber;
    private String email;
    private EGender gender;
    private ERoleType roleId;
    private MarkdownDataDto Markdown;
    private PositionDataDto positionData;
    private EPosition positionId;
    @JsonProperty(value = "Doctor_Infor")
    private DoctorExtraDto doctorInfor;

}
