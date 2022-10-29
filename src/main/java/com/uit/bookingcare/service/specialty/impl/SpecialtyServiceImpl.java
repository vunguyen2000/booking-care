package com.uit.bookingcare.service.specialty.impl;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.specialty.SpecialtyDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.mapper.specialty.SpecialtyMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.specialty.UpdateSpecialtyRequest;
import com.uit.bookingcare.service.specialty.SpecialtyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyMapper specialtyMapper;

    private final SpecialtyRepository specialtyRepository;
    @Override
    public void save(CreateSpecialtyRequest request) {

    }

    @Override
    public List<SpecialtyDto> findAll() {
        return specialtyMapper.toSpecialtyDtoList(specialtyRepository.findAll());
    }

    @Override
    public SpecialtyDto findById(Long id) {
        return specialtyMapper.toSpecialtyDto(specialtyRepository.findById(id).orElse(null));
    }

    @Override
    public void update(Long id, UpdateSpecialtyRequest request) {
        Specialty oldSpecialty = specialtyRepository.findById(id).orElse(null);
        if (oldSpecialty == null){
            return;
        }
        specialtyMapper.updateSpecialty(request, oldSpecialty);
        specialtyRepository.save(oldSpecialty);
    }
}
