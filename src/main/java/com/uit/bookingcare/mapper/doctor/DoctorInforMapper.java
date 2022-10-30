package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.dto.schedule.DoctorScheduleDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class DoctorInforMapper implements MapperBase {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.phonenumber", target = "phonenumber")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "position", target = "positionId")
    public abstract DoctorInforDto doctorInforDto(DoctorInfor doctorInfor);
    public abstract List<DoctorInforDto> doctorInforDtoList(List<DoctorInfor> doctorInfors);

    @Named("updateDoctorInfor")
    @BeforeMapping
    protected void updateDoctorInforBefore(UpdateDoctorInforRequest dto, @MappingTarget DoctorInfor entity) {
        if (dto.getClinicId() != null){
            clinicRepository.findById(dto.getClinicId()).ifPresent(entity::setClinic);
        }

        if (dto.getSpecialtyId() != null){
            specialtyRepository.findById(dto.getSpecialtyId()).ifPresent(entity::setSpecialty);
        }
    }

    @BeanMapping(qualifiedByName = "updateDoctorInfor", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "contentHTML", target = "descriptionHTML")
    @Mapping(source = "contentMarkdown", target = "descriptionMarkdown")
    @Mapping(source = "selectedPrice", target = "price")
    @Mapping(source = "selectedPayment", target = "payment")
    @Mapping(source = "selectedProvince", target = "province")
    public abstract void updateDoctorInfor(UpdateDoctorInforRequest dto, @MappingTarget DoctorInfor entity);




}
