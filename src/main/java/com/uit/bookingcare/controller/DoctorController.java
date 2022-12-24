package com.uit.bookingcare.controller;


import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.booking.SendRemedyDto;
import com.uit.bookingcare.request.doctor.BulkCreateSchedule;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.service.booking.BookingService;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Doctor APIs")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private  final BookingService bookingService;
    @ApiOperation(value = "get all doctors", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/doctors")
    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.findAll()));
    }
    @ApiOperation(value = "get top doctor", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/top-doctor-home")
    public ResponseEntity<?> getTopDoctor(@RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getTopDoctor(limit)));
    }
    @ApiOperation(value = "get detail doctor by id", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/doctors/{id}")
    public ResponseEntity<?> getDetailDoctorById(@PathVariable(value = "id", required = false) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getDetailDoctorById(id)));
    }
    @ApiOperation(value = "save infor doctor", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/save-infor-doctor")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> editDoctor(@RequestBody UpdateDoctorInforRequest request) {
        doctorService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "get schedule doctor by date", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/get-schedule-doctor-by-date")
    public ResponseEntity<?> getScheduleDoctorByDate(@RequestParam(value = "doctorId") Long doctorId,
                                                     @RequestParam(value = "date") Long date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getScheduleDoctorByDate(doctorId, date)));
    }
    @ApiOperation(value = "get extra infor doctor by id", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/get-extra-infor-doctor-by-id")
    public ResponseEntity<?> getExtraDoctorById(@RequestParam(value = "doctorId", required = false) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getExtraDoctorById(id)));
    }
    @ApiOperation(value = "get list patient for doctor", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/get-list-patient-for-doctor")
//    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> getPatientDoctorByDate(@RequestParam(value = "doctorId") Long doctorId,
                                                    @RequestParam(value = "date") Long date) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getListPatientDoctorByDate(doctorId, date)));
    }
    @ApiOperation(value = "get profile doctor by id", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/get-profile-doctor-by-id")
    public ResponseEntity<?> getProfileDoctorById(@RequestParam(value = "doctorId", required = false) Long doctorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.getProfileDoctorById(doctorId)));
    }
    @ApiOperation(value = "bulk create schedule", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/bulk-create-schedule")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> bulkCreateSchedule(@RequestBody BulkCreateSchedule request) {
        doctorService.bulkCreateSchedule(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "search doctor and clinic", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/search")
    public ResponseEntity<?> search(@RequestParam(value = "text", required = false) String text) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(doctorService.search(text)));
    }
    @ApiOperation(value = "send -remedy", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/send-remedy")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> sendRemedy(@RequestBody SendRemedyDto request) {
        bookingService.sendRemedy(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
}
