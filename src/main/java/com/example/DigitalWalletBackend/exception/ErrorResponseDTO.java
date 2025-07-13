package com.example.DigitalWalletBackend.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponseDTO(LocalDateTime timeStamp, int status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorResponseDTO{" +
                "timeStamp=" + timeStamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
