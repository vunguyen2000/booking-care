package com.uit.bookingcare.request.user;

import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.ERoleType;
import com.uit.bookingcare.constant.enums.EUserType;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phonenumber;
    private EGender gender;
    private ERoleType roleId;
    private EUserType userType;
    private String image;
}
