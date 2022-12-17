package com.uit.bookingcare.service.clinic.impl;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.clinics.join.ClinicSpecialty;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.dto.clinics.ClinicDto;
import com.uit.bookingcare.dto.doctor.SearchDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.clinic.ClinicSpecialtyRepository;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.service.clinic.ClinicService;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import com.uit.bookingcare.service.exception.InvalidException;
import com.uit.bookingcare.service.exception.NotFoundException;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicMapper clinicMapper;

    private final ClinicRepository clinicRepository;

    private final ClinicSpecialtyRepository clinicSpecialtyRepository;
    private final DoctorService doctorService;
    @Autowired
    private MessageHelper messageHelper;

    @Override
    public void save(CreateClinicRequest request) {
        Clinic clinic = clinicRepository.findByNameContainingIgnoreCase(request.getName()).orElse(null);
        if (clinic != null) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.Clinic.EXIST));
        }
        clinicRepository.save(clinicMapper.toClinic(request));
    }

    @Override
    public void update(Long id, UpdateClinicRequest request) {
        Clinic oldClinic = clinicRepository.findById(id).orElse(null);
        if (oldClinic == null) {
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
    public ClinicDto findById(Long id) {

        Clinic oldClinic = clinicRepository.findById(id).orElse(null);
        Integer count = oldClinic.getCount() == null ? 1 : oldClinic.getCount() + 1;
        oldClinic.setCount(count);
        clinicRepository.save(oldClinic);
        ClinicDto clinicDto = clinicMapper.toClinicDto(clinicRepository.findById(id).orElse(null));

        List<ClinicSpecialty> clinicSpecialties = clinicSpecialtyRepository.findByClinicId(clinicDto.getId());
        clinicDto.setDoctorInforSchedules(doctorService.getScheduleDoctors(clinicSpecialties.stream().map(cs -> cs.getDoctorInfor().getId()).collect(Collectors.toList())));
        return clinicDto;
    }

    @Override
    public SearchDto search(String text) {
        SearchDto dto = new SearchDto();

        dto.setClinics(clinicMapper.clinicSearchDtoList(clinicRepository.findAllByNameContainingIgnoreCase(text)));
        return dto;
    }

    @Override
    public List<ClinicDto> getTopClinic(Integer limit) {
        Pageable pageable;
        if (limit == null) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "count"));
        }
        return clinicMapper.toClinicDtoList(clinicRepository.findAll(pageable).getContent());
    }


}
