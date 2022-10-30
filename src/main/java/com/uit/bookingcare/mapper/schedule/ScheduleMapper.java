package com.uit.bookingcare.mapper.schedule;

import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DoctorDataDto;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.dto.schedule.DoctorScheduleDto;
import com.uit.bookingcare.dto.schedule.TimeTypeDataDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ScheduleMapper implements MapperBase {

    @Autowired
    private UserRepository userRepository;
    @Named("toDoctorScheduleDto")
    @BeforeMapping
    protected void toDoctorScheduleDto(Schedule schedule, @MappingTarget DoctorScheduleDto dto) {
        ETimeType timeType = schedule.getTimeType();
        User user = userRepository.findById(schedule.getDoctorInfor().getId()).orElse(null);
        dto.setTimeTypeData(new TimeTypeDataDto(timeType.getValueEn(), timeType.getValueVi()));
        if (user != null){
            dto.setDoctorData(new DoctorDataDto(user.getFirstName(), user.getLastName()));
        }
    }

    @BeanMapping(qualifiedByName = "toDoctorScheduleDto")
    @Mapping(source = "doctorInfor.id", target = "doctorId")
    public abstract DoctorScheduleDto toDoctorScheduleDto(Schedule schedule);

    public abstract List<DoctorScheduleDto> toDoctorScheduleDtoList(List<Schedule> schedules);

}
