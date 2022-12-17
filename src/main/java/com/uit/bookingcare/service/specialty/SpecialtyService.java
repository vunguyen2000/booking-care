package com.uit.bookingcare.service.specialty;

import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.doctor.SearchDto;
import com.uit.bookingcare.dto.specialty.SpecialtyDto;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.specialty.UpdateSpecialtyRequest;

import java.util.List;

public interface SpecialtyService {
    void save(CreateSpecialtyRequest request);
    List<SpecialtyDto> findAll();
    SpecialtyDto findById(Long id);
    void update(Long id, UpdateSpecialtyRequest request);
    SearchDto search(String text);
}
