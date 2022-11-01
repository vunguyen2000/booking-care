package com.uit.bookingcare.service.user;


import com.uit.bookingcare.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll(String id);

}
