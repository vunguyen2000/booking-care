package com.uit.bookingcare.service.user;


import com.uit.bookingcare.dto.user.UserDto;
import com.uit.bookingcare.request.specialty.CreateSpecialtyRequest;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;

import java.util.List;

public interface UserService {
    List<UserDto> findAll(String id);

    void save(CreateUserRequest request);
    void update(UpdateUserRequest request);
}
