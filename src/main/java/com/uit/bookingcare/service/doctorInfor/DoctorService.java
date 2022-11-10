package com.uit.bookingcare.service.doctorInfor;

import com.uit.bookingcare.dto.doctor.DetailDoctorDataDto;
import com.uit.bookingcare.dto.doctor.DoctorExtraDto;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.dto.doctor.SearchDto;
import com.uit.bookingcare.dto.schedule.DoctorPatientBookingDto;
import com.uit.bookingcare.dto.schedule.DoctorScheduleDto;
import com.uit.bookingcare.request.doctor.BulkCreateSchedule;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;

import java.time.LocalDate;
import java.util.List;

public interface DoctorService {
    List<DoctorInforDto> findAll();

    List<DoctorInforDto> getTopDoctor(Integer limit);

    DetailDoctorDataDto getDetailDoctorById(Long id);

    List<DoctorScheduleDto> getScheduleDoctorByDate(Long doctorId, Long date);

    void save(UpdateDoctorInforRequest request);

    DoctorExtraDto getExtraDoctorById(Long doctorId);

    List<DoctorPatientBookingDto> getListPatientDoctorByDate(Long doctorId, Long date);
    DetailDoctorDataDto getProfileDoctorById(Long doctorId);

    void bulkCreateSchedule(BulkCreateSchedule request);

    SearchDto search(String text);


}
