package com.uit.bookingcare.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uit.bookingcare.dto.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto {

    @JsonProperty("created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonProperty("updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
