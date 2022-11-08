package com.uit.bookingcare.service.clinic.impl;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.service.clinic.ClinicService;
import com.uit.bookingcare.service.exception.InvalidException;
import com.uit.bookingcare.service.exception.NotFoundException;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicMapper clinicMapper;

    private final ClinicRepository clinicRepository;
    @Autowired
    private MessageHelper messageHelper;
    @Override
    public void save(CreateClinicRequest request) {
        Clinic clinic = clinicRepository.findByNameContainingIgnoreCase(request.getName());
        if(clinic !=null){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Clinic.EXIST));
        }
        clinicRepository.save(clinicMapper.toClinic(request));
    }

    @Override
    public void update(Long id, UpdateClinicRequest request) {
        Clinic oldClinic = clinicRepository.findById(id).orElse(null);
        if (oldClinic == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Clinic.NOT_FOUND));
        }
        clinicMapper.updateClinic(request, oldClinic);
        clinicRepository.save(oldClinic);
    }

    @Override
    public List<ClinicDto> findAll() {
        return clinicMapper.toClinicDtoList(clinicRepository.findAll());
    }

    @Override
    public ClinicDto findById (Long id){

        return clinicMapper.toClinicDto(clinicRepository.findById(id).orElse(null));
    }


}
