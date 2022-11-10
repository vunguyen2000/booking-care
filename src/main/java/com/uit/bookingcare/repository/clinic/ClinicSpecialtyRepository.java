package com.uit.bookingcare.repository.clinic;

import com.uit.bookingcare.domain.clinics.join.ClinicSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicSpecialtyRepository extends JpaRepository<ClinicSpecialty, Long> {

    Optional<ClinicSpecialty> findByClinicIdAndSpecialtyIdAndDoctorInforId(Long clinicId, Long specialtyId, Long doctorId);
    Optional<ClinicSpecialty> findFirstByDoctorInforId(Long doctorId);

}
