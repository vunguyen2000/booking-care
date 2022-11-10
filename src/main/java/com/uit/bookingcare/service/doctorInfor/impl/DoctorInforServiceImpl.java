package com.uit.bookingcare.service.doctorInfor.impl;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DetailDoctorDataDto;
import com.uit.bookingcare.dto.doctor.DoctorExtraDto;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.dto.doctor.SearchDto;
import com.uit.bookingcare.dto.schedule.DoctorPatientBookingDto;
import com.uit.bookingcare.dto.schedule.DoctorScheduleDto;
import com.uit.bookingcare.mapper.clinics.ClinicMapper;
import com.uit.bookingcare.mapper.doctor.DoctorInforMapper;
import com.uit.bookingcare.mapper.doctor.UserMapper;
import com.uit.bookingcare.mapper.schedule.ScheduleMapper;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.schedule.ScheduleRepository;
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

import java.time.LocalDate;
import java.util.List;

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

    private final UserMapper userMapper;
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
            pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        }
        return doctorInforMapper.doctorInforDtoList(doctorInforReposioty.findAll(pageable).getContent());
    }


    @Override
    public DetailDoctorDataDto getDetailDoctorById(Long id) {

        User doctor = userRepository.findById(id).orElse(null);
        if (doctor == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.DoctorInfor.NOT_FOUND));
        }
        return userMapper.detailDoctorDataDto(doctor);
    }

    @Override
        public  List<DoctorScheduleDto> getScheduleDoctorByDate(Long doctorId, Long date) {
        return scheduleMapper.toDoctorScheduleDtoList(scheduleRepository.findAllByDoctorIdAndScheduleDate(doctorId, date));
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
        if(doctorInfor ==null){
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
        return dto;
    }


}
