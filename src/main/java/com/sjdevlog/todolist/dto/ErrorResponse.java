package com.sjdevlog.todolist.dto;

import java.time.LocalDateTime;
public class ErrorResponse {
    private final String code;
    private final String message;
    private final int status;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String code, String message, int status, String path) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public int getStatus() { return status; }
    public String getPath() { return path; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
