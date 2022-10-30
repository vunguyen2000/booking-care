package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceTypeDataDto {
    private Integer valueEn;
    private String valueVi;

    public PriceTypeDataDto(Integer valueEn, String valueVi) {
        this.valueEn = valueEn;
        this.valueVi = valueVi;
    }
}
