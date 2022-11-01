package com.uit.bookingcare.repository.booking;

import com.uit.bookingcare.domain.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
