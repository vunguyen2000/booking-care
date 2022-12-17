package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.specialty.UpdateSpecialtyRequest;
import com.uit.bookingcare.service.specialty.SpecialtyService;
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
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    @ApiOperation(value = "Create new specialty", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/specialties")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createSpecialty(@RequestBody CreateSpecialtyRequest request) {
        specialtyService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "get all specialty", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/specialties")
    public ResponseEntity<?> getSpecialty() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(specialtyService.findAll()));
    }
    @ApiOperation(value = "edit specialty", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/specialties")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> editClinic(@RequestParam Long id,
                                        @RequestBody UpdateSpecialtyRequest request) {
        specialtyService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "Get detail specialty by id", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/specialties/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(specialtyService.findById(id)));
    }
    @ApiOperation(value = "search specialty", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/specialties/search")
    public ResponseEntity<?> search(@RequestParam(value = "text", required = false) String text) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(specialtyService.search(text)));
    }
}
