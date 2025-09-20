package com.example.common.error;


import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    EntityNotFoundException(
            ExceptionCode exceptionCode
    ) {
        this.exceptionCode = exceptionCode;
    }

    EntityNotFoundException(
            ExceptionCode exceptionCode,
            String message
    ) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
