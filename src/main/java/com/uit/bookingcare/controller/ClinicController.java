package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.clinic.UpdateClinicRequest;
import com.uit.bookingcare.service.clinic.ClinicService;
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
@Api(value = "Clinic APIs")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;
    @ApiOperation(value = "Create new clinic", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/clinics")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createClinic(@RequestBody CreateClinicRequest request) {
        clinicService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "Get all clinic", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/clinics")
    public ResponseEntity<?> getClinic() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findAll()));
    }
    @ApiOperation(value = "get top clinic", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/top-clinic-home")
    public ResponseEntity<?> getTopClinic(@RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.getTopClinic(limit)));
    }

    @ApiOperation(value = "Edit clinic", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/clinics")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> editClinic(@RequestParam Long id,
                                        @RequestBody UpdateClinicRequest request) {
        clinicService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "Get detail clinic by id", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/clinics/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findById(id)));
    }

    @ApiOperation(value = "search clinic", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/clinics/search")
    public ResponseEntity<?> search(@RequestParam(value = "text", required = false) String text) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.search(text)));
    }
}
