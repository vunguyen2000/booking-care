package com.uit.bookingcare.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiResponse {

    /**
     * Is success.
     */
    @Getter
    @Setter
    private boolean success;

    /**
     * Message response.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private String message;

    /**
     * Data response.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private Object result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private Long errCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private String errMessage;

    public ApiResponse(Object data) {
        this(null, data);
    }

    public ApiResponse(boolean success, String message) {
        this(success, message, null);
    }


    public ApiResponse(String message, Object data) {
        this(true, message, data);
    }

    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errCode = 0L;
        this.errMessage = "OK";
        if (message != null){
            if (success) {
                log.info(message);
            } else {
                log.error(message);
            }
        }
    }

}

