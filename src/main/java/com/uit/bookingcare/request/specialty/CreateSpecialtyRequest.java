package com.uit.bookingcare.request.specialty;

import lombok.Data;

@Data
public class CreateSpecialtyRequest {
    private String name;
    private String imageBase64;
    private String descriptionHTML;
    private String descriptionMarkdown;
}
