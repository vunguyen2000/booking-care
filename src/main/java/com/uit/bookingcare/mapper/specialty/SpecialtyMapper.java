package com.uit.bookingcare.mapper.specialty;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.clinics.ClinicSearchDto;
import com.uit.bookingcare.dto.specialty.SpecialtyDto;
import com.uit.bookingcare.dto.specialty.SpecialtySearchDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.specialty.UpdateSpecialtyRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class SpecialtyMapper implements MapperBase {

    @Mapping(source = "imageBase64", target = "image")
    public abstract Specialty toSpecialty(CreateSpecialtyRequest request);

    public abstract SpecialtyDto toSpecialtyDto(Specialty specialty);

    public abstract List<SpecialtyDto> toSpecialtyDtoList(List<Specialty> specialties);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateSpecialty(UpdateSpecialtyRequest dto, @MappingTarget Specialty entity);


    public abstract SpecialtySearchDto specialtySearchDto(Specialty specialty);

    public abstract List<SpecialtySearchDto> specialtySearchDtoList(List<Specialty> Specialties);
}
