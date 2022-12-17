package com.uit.bookingcare.repository.clinic;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {


    Optional<Clinic> findById(Long id);


    @Query(value = "select * from clinics c join clinic_specialty ON clinic_specialty.clinic_id = c.id\n" +
            "join doctor_infor ON doctor_infor.id = clinic_specialty.doctor_id\n" +
            "Where doctor_infor.id = :id", nativeQuery = true)
    Optional<Clinic> findByDoctorInForId(Long id);
    List<Clinic> findAllByNameContainingIgnoreCase(String name);

    Optional<Clinic> findByNameContainingIgnoreCase(String name);
    List<Clinic> findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);


    @Query(value = "SELECT s.* FROM schedule s JOIN booking b ON s.id = b.schedule_id " +
            " WHERE s.doctor_id = :doctorId AND s.date = :date", nativeQuery = true)
    List<Schedule> findAllByDoctorIdAndPatientDate(Long doctorId, Long date);


}
