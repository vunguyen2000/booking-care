package com.uit.bookingcare.mapper.patient;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.patient.Patient;
import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PatientMapper {

    public abstract Patient toPatient(Long id);
}
