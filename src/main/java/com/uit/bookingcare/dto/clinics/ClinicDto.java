package com.uit.bookingcare.dto.clinics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uit.bookingcare.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClinicDto extends BaseDto {

    private Long id;

    private String name;

    private String address;
    private String descriptionHTML;
    private String descriptionMarkdown;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicDto clinicDto = (ClinicDto) o;
        return Objects.equals(id, clinicDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
