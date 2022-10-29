package com.uit.bookingcare.service.doctorInfor;

import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;

import java.util.List;

public interface DoctorService {
    List<DoctorInforDto> findAll();

    List<DoctorInforDto> getTopDoctor(Integer limit);

    DoctorInforDto getDoctorById(Long id);

    void save(UpdateDoctorInforRequest request);

}
