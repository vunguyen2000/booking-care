package com.uit.bookingcare.repository.specialty;

import com.uit.bookingcare.domain.speciatly.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
   Optional<Specialty> findByNameContainingIgnoreCase(String name);
}
