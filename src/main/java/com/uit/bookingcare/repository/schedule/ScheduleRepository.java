package com.uit.bookingcare.repository.schedule;


import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.speciatly.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT s.* FROM doctor_infor d JOIN schedule s ON d.id = s.doctor_id " +
            " WHERE d.id = :doctorId AND s.date = :date", nativeQuery = true)
    List<Schedule> findAllByDoctorIdAndScheduleDate(Long doctorId, Long date);
    @Query(value = "SELECT s.* FROM schedule s JOIN booking b ON s.id = b.schedule_id " +
            " WHERE s.doctor_id = :doctorId AND s.date = :date AND b.status_id='S2'", nativeQuery = true)
    List<Schedule> findAllByDoctorIdAndPatientDate(Long doctorId, Long date);

    @Query(value = "SELECT s.* FROM doctor_infor d JOIN schedule s ON d.id = s.doctor_id " +
            " WHERE d.id = :doctorId", nativeQuery = true)
    List<Schedule> findAllByDoctorId(Long doctorId);

}
