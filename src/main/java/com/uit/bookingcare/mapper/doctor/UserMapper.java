package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DetailDoctorDataDto;
import com.uit.bookingcare.dto.doctor.PositionDataDto;
import com.uit.bookingcare.dto.user.UserDto;
import com.uit.bookingcare.mapper.MapperBase;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper implements MapperBase {


    @Autowired
    private DoctorInforMapper doctorInforMapper;

    @Named("detailDoctorDataDto")
    @BeforeMapping
    protected void detailDoctorDataDto(User user, @MappingTarget DetailDoctorDataDto dto) {
        EPosition position = user.getDoctorInfor().getPosition();
        dto.setPositionData(new PositionDataDto(position.getValueEn(), position.getValueVi()));
        dto.setDoctorInfor(doctorInforMapper.toExtraDoctorInforDto(user.getDoctorInfor()));
    }

    @BeanMapping(qualifiedByName = "detailDoctorDataDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.phonenumber", target = "phonenumber")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "user.doctorInfor.position", target = "positionId")
    public abstract DetailDoctorDataDto detailDoctorDataDto(User user);



    @BeanMapping(qualifiedByName = "userDto", ignoreByDefault = false,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "user.doctorInfor.position", target = "positionId")
    public abstract UserDto userDto(User user);
    public abstract List<UserDto> userDtoList(List<User> users);

}
