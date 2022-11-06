package com.uit.bookingcare.mapper.booking;

import com.uit.bookingcare.constant.enums.EPayment;
import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.booking.BookingRepository;
import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.booking.SendRemedyDto;
import com.uit.bookingcare.request.booking.VerifyBookAppointmentDto;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class BookingMapper implements MapperBase {
    @Autowired
    private BookingRepository bookingRepository;

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toBooking(VerifyBookAppointmentDto dto, @MappingTarget Booking entity);


    @Named("createNewBooking")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "scheduleId", target = "schedule.id")
    @Mapping(source = "patientId", target = "patient.id")
    public abstract Booking createNewBooking(PostBookAppointment request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toSendRemeDy(SendRemedyDto dto, @MappingTarget Booking entity);

}
