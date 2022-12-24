package com.uit.bookingcare.service.doctorInfor.impl;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.*;
import com.uit.bookingcare.dto.schedule.DoctorPatientBookingDto;
import com.uit.bookingcare.dto.schedule.ScheduleDoctorDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.mapper.doctor.DoctorInforMapper;
import com.uit.bookingcare.mapper.doctor.UserMapper;
import com.uit.bookingcare.mapper.schedule.ScheduleMapper;
import com.uit.bookingcare.mapper.specialty.SpecialtyMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.schedule.ScheduleRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.doctor.BulkCreateSchedule;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import com.uit.bookingcare.service.exception.BadRequestException;
import com.uit.bookingcare.service.exception.NotFoundException;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorInforServiceImpl implements DoctorService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;
    private final DoctorInforMapper doctorInforMapper;

    private final DoctorInforRepository doctorInforReposioty;

    private final ScheduleMapper scheduleMapper;

    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;

    private final SpecialtyMapper specialtyMapper;
    private final UserMapper userMapper;
    private final SpecialtyRepository specialtyRepository;
    @Autowired
    private MessageHelper messageHelper;


    @Override
    public List<DoctorInforDto> findAll() {

        return doctorInforMapper.doctorInforDtoList(doctorInforReposioty.findAll());

    }

    @Override
    public List<DoctorInforDto> getTopDoctor(Integer limit) {
        Pageable pageable;
        if (limit == null) {
            pageable = Pageable.unpaged();
        } else {
            pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "count"));
        }
        return doctorInforMapper.doctorInforDtoList(doctorInforReposioty.findAll(pageable).getContent());
    }


    @Override
    public DetailDoctorDataDto getDetailDoctorById(Long id) {
        User doctor = userRepository.findById(id).orElse(null);
        if (doctor == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.DoctorInfor.NOT_FOUND));
        }
        DoctorInfor oldDoctor = doctor.getDoctorInfor();
        Integer count = oldDoctor.getCount() == null ? 1 : oldDoctor.getCount() + 1;
        oldDoctor.setCount(count);
        doctorInforReposioty.save(oldDoctor);

        Clinic oldClinic = clinicRepository.findByDoctorInForId(id).orElse(null);
        Integer count1 = oldClinic.getCount() == null ? 1 : oldClinic.getCount() + 1;
        oldClinic.setCount(count1);
        clinicRepository.save(oldClinic);

        return userMapper.detailDoctorDataDto(doctor);
    }

    @Override
    public List<ScheduleDoctorDto> getScheduleDoctorByDate(Long doctorId, Long date) {
        return scheduleMapper.toDoctorScheduleDtoList(scheduleRepository.findAllByDoctorIdAndScheduleDate(doctorId, date));
    }

    @Override
    public List<DoctorInforScheduleDto> getScheduleDoctors(List<Long> doctorIds) {
        List<DoctorInforScheduleDto> rs = new ArrayList<>();
        for (Long doctorId : doctorIds) {
            DoctorInforScheduleDto dto = new DoctorInforScheduleDto();
            dto.setDoctor(this.getDetailDoctorById(doctorId));
            dto.setSchedules(scheduleMapper.toScheduleDtoList(scheduleRepository.findAllByDoctorId(doctorId)));
            rs.add(dto);
        }
        return rs;
    }

    @Override
    public void save(UpdateDoctorInforRequest request) {
        if (request.getDoctorId() == null) {
            throw new BadRequestException(messageHelper.getMessage(MessageCode.DoctorInfor.INVALID));
        }

        DoctorInfor oldDoctor = doctorInforReposioty.findById(request.getDoctorId()).orElse(null);
        if (oldDoctor == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.DoctorInfor.NOT_FOUND));
        }
        doctorInforMapper.updateDoctorInfor(request, oldDoctor);
        doctorInforReposioty.save(oldDoctor);
    }


    @Override
    public DoctorExtraDto getExtraDoctorById(Long doctorId) {
        return doctorInforMapper.toExtraDoctorInforDto(doctorInforReposioty.findById(doctorId).orElse(null));
    }

    @Override
    public List<DoctorPatientBookingDto> getListPatientDoctorByDate(Long doctorId, Long date) {
        DoctorInfor doctorInfor = doctorInforReposioty.findById(doctorId).orElse(null);
        if (doctorInfor == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.DoctorInfor.NOT_FOUND));
        }
        return scheduleMapper.toDoctorPatientBookingDtoList(scheduleRepository.findAllByDoctorIdAndPatientDate(doctorId, date));

    }

    @Override
    public DetailDoctorDataDto getProfileDoctorById(Long doctorId) {
        return userMapper.detailDoctorDataDto(userRepository.findById(doctorId).orElse(null));
    }

    @Override
    public void bulkCreateSchedule(BulkCreateSchedule request) {
        scheduleRepository.save(doctorInforMapper.bulkCreateSchedule(request));
    }

    @Override
    public SearchDto search(String text) {
        SearchDto dto = new SearchDto();
        dto.setDoctors(doctorInforMapper.doctorSearchDtoList(doctorInforReposioty.findAllByUserFirstNameContainingIgnoreCase(text)));
        dto.setClinics(clinicMapper.clinicSearchDtoList(clinicRepository.findAllByNameContainingIgnoreCase(text)));
        dto.setSpecialties(specialtyMapper.specialtySearchDtoList(specialtyRepository.findAllByNameContainingIgnoreCase(text)));
        return dto;
    }


}
