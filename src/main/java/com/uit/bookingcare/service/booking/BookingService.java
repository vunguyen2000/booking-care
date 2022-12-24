package com.uit.bookingcare.service.booking;

import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.booking.SendRemedyDto;
import com.uit.bookingcare.request.booking.VerifyBookAppointmentDto;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;

public interface BookingService {
    void update(String token);
    void create(PostBookAppointment request);

    void sendRemedy(SendRemedyDto request);


//    void verify(VerifyBookAppointmentDto request);
}
