package com.uit.bookingcare.service.doctorInfor.impl;

import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.dto.schedule.DoctorScheduleDto;
import com.uit.bookingcare.mapper.doctor.DoctorInforMapper;
import com.uit.bookingcare.mapper.schedule.ScheduleMapper;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.schedule.ScheduleRepository;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorInforServiceImpl implements DoctorService {

    private final DoctorInforMapper doctorInforMapper;

    private final DoctorInforRepository doctorInforReposioty;

    private final ScheduleMapper scheduleMapper;

    private final ScheduleRepository scheduleRepository;


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
    public DoctorInforDto getDoctorById(Long id) {

        return doctorInforMapper.doctorInforDto(doctorInforReposioty.findById(id).orElse(null));
    }

    @Override
        public  List<DoctorScheduleDto> getScheduleDoctorByDate(Long doctorId, LocalDate date) {
        return scheduleMapper.toDoctorScheduleDtoList(scheduleRepository.findAllByDoctorIdAndScheduleDate(doctorId, date));
    }

    @Override
    public void save(UpdateDoctorInforRequest request) {
        if (request.getDoctorId() == null) {
            return;
        }

        DoctorInfor oldDoctor = doctorInforReposioty.findById(request.getDoctorId()).orElse(null);
        if (oldDoctor == null) {
            return;
        }
        doctorInforMapper.updateDoctorInfor(request, oldDoctor);
        doctorInforReposioty.save(oldDoctor);
    }

}
