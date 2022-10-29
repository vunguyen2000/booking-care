package com.uit.bookingcare.repository.specialty;

import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.speciatly.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

}
