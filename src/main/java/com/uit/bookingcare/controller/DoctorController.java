package com.uit.bookingcare.controller;


import com.uit.bookingcare.service.doctorInfor.IFindAllUserDoctorInforService;
import com.uit.bookingcare.dto.response.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Doctor APIs")
@RequiredArgsConstructor
public class DoctorController {


    private final IFindAllUserDoctorInforService findAllUserDoctorInforService;

//
//    @ApiOperation(value = "Search DoctorInfor", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/searchv3")
    public ResponseEntity<?> findAllUserDoctorInfor(@RequestParam(value = "lastname", required = false) String lastname) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllUserDoctorInforService.execute(lastname)));

    }
}
