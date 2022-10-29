package com.uit.bookingcare.request.clinic;

import lombok.Data;

@Data
public class UpdateClinicRequest {
    private String name;
    private String image;
    private String address;
    private String descriptionHTML;
    private String descriptionMarkdown;
}
