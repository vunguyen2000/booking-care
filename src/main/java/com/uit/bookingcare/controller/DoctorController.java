package com.uit.bookingcare.controller;


import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.service.clinic.ClinicService;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getTopDoctor(@RequestParam(value = "limit",required = false) Integer limit) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getTopDoctor(limit)));
    }
    @GetMapping(value = "/get-detail-doctor-by-id")
    public ResponseEntity<?> getDoctorById(@RequestParam(value = "id",required = false) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getDoctorById(id)));
    }
    @PostMapping(value = "/save-infor-doctor")
    public ResponseEntity<?> editDoctor(@RequestBody UpdateDoctorInforRequest request) {
        doctorService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
}
