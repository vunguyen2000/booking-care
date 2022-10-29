package com.uit.bookingcare.service.clinic;

import com.uit.bookingcare.dto.clinics.ClinicDto;

import java.util.List;
import java.util.Optional;

public interface ClinicService {

    List<ClinicDto> findAllByName(String name);

    List<ClinicDto> findAllByNameOrAddress(String name, String address);

    ClinicDto findById(Long id);
}
