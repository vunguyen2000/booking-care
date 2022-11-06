package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.patient.Patient;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.auth.UserLoginDto;
import com.uit.bookingcare.dto.doctor.DetailDoctorDataDto;
import com.uit.bookingcare.dto.doctor.MarkdownDataDto;
import com.uit.bookingcare.dto.doctor.PositionDataDto;
import com.uit.bookingcare.dto.user.UserDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        dto.setMarkdown(new MarkdownDataDto(user.getDoctorInfor().getDescription(), user.getDoctorInfor().getContentHTML(), user.getDoctorInfor().getContentMarkdown()));
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
    @Mapping(source = "user.doctorInfor.id", target = "id")
    public abstract DetailDoctorDataDto detailDoctorDataDto(User user);


    @BeanMapping(qualifiedByName = "userDto", ignoreByDefault = false,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "user.doctorInfor.position", target = "positionId")
    public abstract UserDto userDto(User user);

    public abstract List<UserDto> userDtoList(List<User> users);

    @Mapping(source = "roleId", target = "userType")
    @Mapping(source = "roleType", target = "role.id")
    public abstract User toUser(CreateUserRequest request);


    @BeanMapping(qualifiedByName = "updateUser", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roleId", target = "role.id")
    public abstract void updateUser(UpdateUserRequest dto, @MappingTarget User entity);

    @Named("createNewPatient")
    @BeforeMapping
    protected void createNewPatient(PostBookAppointment request, @MappingTarget User user) {
        user.setUserType(EUserType.PATIENT);
        user.setPatient(new Patient());
    }

    @BeanMapping(qualifiedByName = "createNewPatient",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "firstName")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "selectedGender", target = "gender")
    @Mapping(source = "phonenumber", target = "phonenumber")
    public abstract User createNewPatient(PostBookAppointment request);


}
