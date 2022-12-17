package com.uit.bookingcare.repository.specialty;

import com.uit.bookingcare.domain.clinics.Clinic;
import
com.uit.bookingcare.domain.speciatly.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
   Optional<Specialty> findByNameContainingIgnoreCase(String name);

   List<Specialty> findAllByNameContainingIgnoreCase(String name);


}
