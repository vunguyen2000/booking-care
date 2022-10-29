package com.uit.bookingcare.service.clinic.impl;

import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.service.clinic.ClinicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicMapper clinicMapper;

    private final ClinicRepository clinicRepository;

    @Override
    public List<ClinicDto> findAllByName(String name) {
        return clinicMapper.toClassDtoList(clinicRepository.findAllByNameContainingIgnoreCase(name));
    }

    @Override
    public List<ClinicDto> findAllByNameOrAddress(String name, String address) {
        return clinicMapper.toClassDtoList(clinicRepository.findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(name, address));
    }

    @Override
    public ClinicDto findById (Long id){
        return clinicMapper.toClinicDto(clinicRepository.findById(id).orElse(null));
    }


}
