package com.uit.bookingcare.repository.clinic;

import com.uit.bookingcare.domain.clinics.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {


    Optional<Clinic> findById(Long id);
    List<Clinic> findAllByNameContainingIgnoreCase(String name);

    Optional<Clinic> findByNameContainingIgnoreCase(String name);
    List<Clinic> findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);

}
