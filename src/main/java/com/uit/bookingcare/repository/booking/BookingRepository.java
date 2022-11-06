package com.uit.bookingcare.repository.booking;

import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Booking findByTokenContainingIgnoreCase(String token);

}
