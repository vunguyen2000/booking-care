package com.uit.bookingcare.request.clinic;

import lombok.Data;

@Data
public class CreateClinicRequest {
    private String name;
    private String imageBase64;
    private String address;
    private String descriptionHTML;
    private String descriptionMarkdown;
}
