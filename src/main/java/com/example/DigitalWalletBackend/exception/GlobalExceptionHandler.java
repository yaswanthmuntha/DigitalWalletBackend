package com.example.DigitalWalletBackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerCustomerNotFoundException(
            HttpServletRequest httpServletRequest,CustomerNotFoundException customerNotFoundException){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                customerNotFoundException.getMessage(),
                httpServletRequest.getRequestURI()
        );

        return new ResponseEntity<>(responseDTO,HttpStatus.NOT_FOUND);
    }
}
