package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.service.clinic.ClinicService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Clinic APIs")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;

//    @ApiOperation(value = "Search Clinic", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/search")
    public ResponseEntity<?> findAllClinicByNameService(@RequestParam(value = "name", defaultValue = "") String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findAllByName(name)));
    }

    @GetMapping(value = "/searchv2")
    public ResponseEntity<?> findAllClinicByNameService2(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "address", required = false) String address) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findAllByNameOrAddress(name,address)));
    }
    @GetMapping(value = "/get-detail-clinic-by-id")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(clinicService.findById(id)));
    }
}
