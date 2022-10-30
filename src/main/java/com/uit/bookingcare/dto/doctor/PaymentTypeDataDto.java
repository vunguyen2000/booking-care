package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTypeDataDto {
    private String valueEn;
    private String valueVi;

    public PaymentTypeDataDto(String valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
