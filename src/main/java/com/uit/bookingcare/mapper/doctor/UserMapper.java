package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DetailDoctorDataDto;
import com.uit.bookingcare.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper implements MapperBase {


    @Autowired
    private DoctorInforMapper doctorInforMapper;

    @Named("detailDoctorDataDto")
    @BeforeMapping
    protected void detailDoctorDataDto(User user, @MappingTarget DetailDoctorDataDto dto) {
       dto.setDoctorInfor(doctorInforMapper.toExtraDoctorInforDto(user.getDoctorInfor()));
    }
    @BeanMapping(qualifiedByName = "detailDoctorDataDto" ,ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(source = "specialty.id", target = "specialtyId")
//    @Mapping(source = "clinic.id", target = "clinicId")
//    @Mapping(source = "price", target = "priceId")
//    @Mapping(source = "province", target = "provinceId")
//    @Mapping(source = "payment", target = "paymentId")
//    @Mapping(source = "clinic.address", target = "addressClinic")
//    @Mapping(source = "clinic.name", target = "nameClinic")
//    @Mapping(source = "count", target = "count")
//    @Mapping(source = "note", target = "note")
//    @Mapping(source = "createdAt", target = "createdAt")

    public abstract DetailDoctorDataDto detailDoctorDataDto(User user);

}
