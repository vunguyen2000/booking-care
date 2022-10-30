package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.specialty.UpdateSpecialtyRequest;
import com.uit.bookingcare.service.specialty.SpecialtyService;
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
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @PostMapping(value = "/create-new-specialty")
    public ResponseEntity<?> createSpecialty(@RequestBody CreateSpecialtyRequest request) {
        specialtyService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @GetMapping(value = "/get-specialty")
    public ResponseEntity<?> getSpecialty() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(specialtyService.findAll()));
    }

    @PutMapping(value = "/edit-specialty")
    public ResponseEntity<?> editClinic(@RequestParam Long id,
                                        @RequestBody UpdateSpecialtyRequest request) {
        specialtyService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }

    @GetMapping(value = "/get-detail-specialty-by-id")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(specialtyService.findById(id)));
    }
}
