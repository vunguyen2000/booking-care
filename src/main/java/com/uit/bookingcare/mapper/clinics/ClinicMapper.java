package com.uit.bookingcare.mapper.clinics;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper(componentModel = "spring")
public abstract class ClinicMapper implements MapperBase {

    @BeanMapping(ignoreByDefault = true)
    @Named("toClinicDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "descriptionHTML", target = "description")
    public abstract ClinicDto toClinicDto(Clinic clinic);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toClinicDtoList")
    public abstract List<ClinicDto> toClassDtoList(List<Clinic> clinics);


}
