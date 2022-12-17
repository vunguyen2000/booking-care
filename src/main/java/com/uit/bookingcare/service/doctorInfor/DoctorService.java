package com.uit.bookingcare.service.doctorInfor;

import com.uit.bookingcare.dto.doctor.*;
import com.uit.bookingcare.dto.schedule.DoctorPatientBookingDto;
import com.uit.bookingcare.dto.schedule.ScheduleDoctorDto;
import com.uit.bookingcare.request.doctor.BulkCreateSchedule;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;

import java.util.List;

public interface DoctorService {
    List<DoctorInforDto> findAll();

    List<DoctorInforDto> getTopDoctor(Integer limit);

    DetailDoctorDataDto getDetailDoctorById(Long id);

    List<ScheduleDoctorDto> getScheduleDoctorByDate(Long doctorId, Long date);

    List<DoctorInforScheduleDto> getScheduleDoctors(List<Long> doctorIds);

    void save(UpdateDoctorInforRequest request);

    DoctorExtraDto getExtraDoctorById(Long doctorId);

    List<DoctorPatientBookingDto> getListPatientDoctorByDate(Long doctorId, Long date);
    DetailDoctorDataDto getProfileDoctorById(Long doctorId);

    void bulkCreateSchedule(BulkCreateSchedule request);

    SearchDto search(String text);


}
