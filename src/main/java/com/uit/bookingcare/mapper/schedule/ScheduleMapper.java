package com.uit.bookingcare.mapper.schedule;

import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DoctorInforScheduleDto;
import com.uit.bookingcare.dto.patient.GenderDataDto;
import com.uit.bookingcare.dto.schedule.DoctorPatientBookingDto;
import com.uit.bookingcare.dto.doctor.DoctorDataDto;
import com.uit.bookingcare.dto.patient.PatientDataDto;
import com.uit.bookingcare.dto.schedule.ScheduleDoctorDto;
import com.uit.bookingcare.dto.schedule.ScheduleDto;
import com.uit.bookingcare.dto.schedule.TimeTypeDataDto;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.booking.BookingRepository;
import com.uit.bookingcare.repository.patient.PatientRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Mapper(componentModel = "spring")
public abstract class ScheduleMapper implements MapperBase {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Named("toDoctorScheduleDto")
    @BeforeMapping
    protected void toDoctorScheduleDto(Schedule schedule, @MappingTarget ScheduleDoctorDto dto) {
        ETimeType timeType = schedule.getTimeType();
        User user = userRepository.findById(schedule.getDoctorInfor().getId()).orElse(null);
        dto.setTimeTypeData(new TimeTypeDataDto(timeType.getValueEn(), timeType.getValueVi()));
        if (user != null){
            dto.setDoctorData(new DoctorDataDto(user.getFirstName(), user.getLastName()));
        }
    }

    @BeanMapping(qualifiedByName = "toDoctorScheduleDto")
    @Mapping(source = "doctorInfor.id", target = "doctorId")
    public abstract ScheduleDoctorDto toDoctorScheduleDto(Schedule schedule);

    public abstract List<ScheduleDoctorDto> toDoctorScheduleDtoList(List<Schedule> schedules);

    @Mapping(source = "doctorInfor.id", target = "doctorId")
    public abstract ScheduleDto toScheduleDto(Schedule schedule);

    public abstract List<ScheduleDto> toScheduleDtoList(List<Schedule> schedules);

    @Named("toDoctorPatientBookingDto")
    @BeforeMapping
    protected void toDoctorPatientBookingDto(Schedule schedule, @MappingTarget DoctorPatientBookingDto dto) {
        ETimeType timeType = schedule.getTimeType();

        Booking booking = bookingRepository.findByScheduleId(schedule.getId()).orElse(null);
        if(booking!=null){
            dto.setStatusId(booking.getStatusId());
            dto.setToken(booking.getToken());
        }
        User user = userRepository.findById(booking.getPatient().getId()).orElse(null);
        dto.setTimeTypeDataPatient(new TimeTypeDataDto(timeType.getValueEn(), timeType.getValueVi()));
        if (user != null){
            EGender gender = user.getGender();
            dto.setPatientData(new PatientDataDto(user.getEmail(),user.getFirstName(),user.getLastName(),user.getAddress(),user.getGender(), new GenderDataDto(gender.getValueEn(),gender.getValueVi())));
        }
        dto.setPatientId(user.getId());
    }

    @BeanMapping(qualifiedByName = "toDoctorPatientBookingDto")
    @Mapping(source = "doctorInfor.id", target = "doctorId")
    public abstract DoctorPatientBookingDto toDoctorPatientBookingDto(Schedule schedule);

    public abstract List<DoctorPatientBookingDto> toDoctorPatientBookingDtoList(List<Schedule> schedules);

}
