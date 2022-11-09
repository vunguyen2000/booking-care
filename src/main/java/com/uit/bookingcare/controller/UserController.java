package com.uit.bookingcare.controller;

import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;
import com.uit.bookingcare.service.doctorInfor.DoctorService;
import com.uit.bookingcare.service.user.UserService;
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
@Api(value = "User APIs")
@RequiredArgsConstructor
public class UserController  {

    private final UserService userService;

    @ApiOperation(value = "get all user", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/get-all-users")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> getAllUser(@RequestParam(value = "id", required = false) String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(userService.findAll(id)));
    }
    @ApiOperation(value = "create new user", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/create-new-user")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        userService.save(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "edit user", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/edit-user")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> editUser(@RequestBody UpdateUserRequest request) {
        userService.update(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
    @ApiOperation(value = "delete user by id", authorizations = {@Authorization(value = "JWT")})
    @DeleteMapping(value = "/delete-user/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse());
    }
}
