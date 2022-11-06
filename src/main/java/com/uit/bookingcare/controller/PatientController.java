package com.uit.bookingcare.controller;

import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.booking.VerifyBookAppointmentDto;
import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.service.booking.BookingService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Patient APIs")
@RequiredArgsConstructor
public class PatientController {
    private final BookingService bookingService;

@PostMapping(value = "/verify-book-appointment")
public ResponseEntity<?> update(@RequestBody VerifyBookAppointmentDto request) {
    bookingService.update(request);
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse());
}
    @PostMapping(value = "/patient-book-appointment")
    public ResponseEntity<?> create(@RequestBody PostBookAppointment request) {
        bookingService.create(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
}
