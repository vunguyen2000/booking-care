package com.uit.bookingcare.service.booking;

import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.booking.SendRemedyDto;
import com.uit.bookingcare.request.booking.VerifyBookAppointmentDto;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;

public interface BookingService {
    void update(VerifyBookAppointmentDto request);
    void create(PostBookAppointment request);

    void sendRemedy(SendRemedyDto request);
}
