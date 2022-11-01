package com.uit.bookingcare.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarkdownDataDto {

    private String description;
    private String contentHTML;
    private String contentMarkdown;

    public MarkdownDataDto(String description, String contentHTML, String contentMarkdown) {
        this.description = description;
        this.contentHTML = contentHTML;
        this.contentMarkdown = contentMarkdown;
    }
}
