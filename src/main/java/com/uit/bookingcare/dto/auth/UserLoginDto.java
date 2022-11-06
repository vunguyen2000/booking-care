package com.uit.bookingcare.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginDto {
    private String email;
    private String password;

    //need default constructor for JSON Parsing
    public UserLoginDto() {
    }

}