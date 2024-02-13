package com.api.eventdrivenuserservice.exception;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

public class ExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<String> message;


    public ExceptionResponse(List<String> message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public List<String> getMessage() {
        return this.message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }


}
