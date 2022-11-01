package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import com.uit.bookingcare.service.user.UserService;
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
@Api(value = "User APIs")
@RequiredArgsConstructor
public class UserController  {

    private final UserService userService;

    @GetMapping(value = "/get-all-users")
    public ResponseEntity<?> getAllUser(@RequestParam(value = "id", required = false) String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(userService.findAll(id)));
    }
}
