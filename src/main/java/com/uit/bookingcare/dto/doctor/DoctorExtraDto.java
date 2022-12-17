package com.uit.bookingcare.dto.doctor;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uit.bookingcare.constant.enums.*;
import com.uit.bookingcare.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorExtraDto extends BaseDto {
    private Long id;
    private String addressClinic;
    private String nameClinic;
    private Long specialtyId;
    private String image;
    private Long clinicId;
    private EPrice priceId;
    private EProvince provinceId;
    private EPayment paymentId;
    private String note;
    private Integer count;
    private PaymentTypeDataDto paymentTypeData;
    private PriceTypeDataDto priceTypeData;
    private ProvinceTypeDataDto provinceTypeData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorExtraDto doctorExtraDto = (DoctorExtraDto) o;
        return Objects.equals(id, doctorExtraDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
