package com.uit.bookingcare.service.sdi;

import lombok.Data;

@Data
public class ClientSdi {
    private String name;
    private String email;
    private String doctorName;
    private String time;
    private String token;
    private String address;
}
