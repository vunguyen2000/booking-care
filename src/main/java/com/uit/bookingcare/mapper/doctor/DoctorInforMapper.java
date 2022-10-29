package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class DoctorInforMapper {

    @BeanMapping(ignoreByDefault = true)
    @Named("toDoctorInforDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    public abstract DoctorInforDto toDoctorInforDto(User users);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toDoctorInforDtoList")
    public abstract List<DoctorInforDto> toDoctorInforDtoList(List<User> users);

}
