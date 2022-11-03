package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import com.uit.bookingcare.service.user.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/create-new-user")
    public ResponseEntity<?> createSpecialty(@RequestBody CreateUserRequest request) {
        userService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }

    @PutMapping(value = "/edit-user")
    public ResponseEntity<?> editUser(@RequestBody UpdateUserRequest request) {
        userService.update(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
}
