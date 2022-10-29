package com.uit.bookingcare.dto.clinics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClinicFullDto {

    private Long id;

    private String name;

    private String description;

    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicFullDto ClinicFullDto = (ClinicFullDto) o;
        return Objects.equals(id, ClinicFullDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
