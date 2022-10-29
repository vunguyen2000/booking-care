package com.uit.bookingcare.service.clinic;

import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;

import java.util.List;

public interface ClinicService {

    void save(CreateClinicRequest request);
    void update(Long id, UpdateClinicRequest request);
    List<ClinicDto> findAll();

    ClinicDto findById(Long id);
}
