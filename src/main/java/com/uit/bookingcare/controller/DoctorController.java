package com.uit.bookingcare.controller;


import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@Api(value = "Doctor APIs")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping(value = "/get-all-doctors")
    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.findAll()));
    }

    @GetMapping(value = "/top-doctor-home")
    public ResponseEntity<?> getTopDoctor(@RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getTopDoctor(limit)));
    }

    @GetMapping(value = "/get-detail-doctor-by-id")
    public ResponseEntity<?> getDoctorById(@RequestParam(value = "id", required = false) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getDoctorById(id)));
    }

    @PostMapping(value = "/save-infor-doctor")
    public ResponseEntity<?> editDoctor(@RequestBody UpdateDoctorInforRequest request) {
        doctorService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }

    @GetMapping(value = "/get-schedule-doctor-by-date")
    public ResponseEntity<?> getScheduleDoctorByDate(@RequestParam(value = "doctorId") Long doctorId,
                                                     @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getScheduleDoctorByDate(doctorId,date)));
    }

    @GetMapping(value = "/get-extra-infor-doctor-by-id")
        public ResponseEntity<?> getExtraDoctorById(@RequestParam(value = "doctorId", required = false) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getExtraDoctorById(id)));
    }

    @GetMapping(value = "/get-list-patient-for-doctor")
    public ResponseEntity<?> getPatientDoctorByDate(@RequestParam(value = "doctorId") Long doctorId,
                                                     @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getPatientDoctorByDate(doctorId,date)));
    }
//    @PostMapping(value = "/bulk-create-schedule")
//    public ResponseEntity<?> editDoctor(@RequestBody UpdateDoctorInforRequest request) {
//        doctorService.save(request);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ApiResponse());
//    }

}
