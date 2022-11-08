package com.uit.bookingcare.mapper.clinics;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.clinics.ClinicSearchDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ClinicMapper implements MapperBase {

    @Mapping(source = "imageBase64", target = "image")
    public abstract Clinic toClinic(CreateClinicRequest request);

    public abstract ClinicDto toClinicDto(Clinic clinic);

    public abstract List<ClinicDto> toClinicDtoList(List<Clinic> clinics);

    public abstract void updateClinic(UpdateClinicRequest dto, @MappingTarget Clinic entity);


    public abstract ClinicSearchDto clinicSearchDto(Clinic clinic);

    public abstract List<ClinicSearchDto> clinicSearchDtoList(List<Clinic> clinics);
}
