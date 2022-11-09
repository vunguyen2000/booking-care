package com.uit.bookingcare.repository.booking;

import com.uit.bookingcare.domain.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findByTokenContainingIgnoreCase(String token);
    @Query(value = "SELECT * FROM  booking b  " +
            " WHERE b.patient_id = :patientId AND b.schedule_id = :scheduleId", nativeQuery = true)
    Optional<Booking> findByPatientIdAndScheduleId(Long patientId, Long scheduleId);
}
