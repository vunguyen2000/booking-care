package com.uit.bookingcare.request.specialty;

import lombok.Data;

@Data
public class UpdateSpecialtyRequest {
    private String name;
    private String image;
    private String descriptionHTML;
    private String descriptionMarkdown;
}
