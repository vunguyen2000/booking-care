package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.service.clinic.ClinicService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Clinic APIs")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;

    @PostMapping(value = "/create-new-clinic")
    public ResponseEntity<?> createClinic(@RequestBody CreateClinicRequest request) {
        clinicService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }

    @GetMapping(value = "/get-clinic")
    public ResponseEntity<?> getClinic() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findAll()));
    }

    @PutMapping(value = "/edit-clinic")
    public ResponseEntity<?> editClinic(@RequestParam Long id,
                                        @RequestBody UpdateClinicRequest request) {
        clinicService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }

    @GetMapping(value = "/get-detail-clinic-by-id")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findById(id)));
    }
}
