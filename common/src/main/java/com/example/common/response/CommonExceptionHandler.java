package com.example.common.response;


import com.example.common.error.EntityNotFoundException;
import com.example.common.error.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> entityNotFoundExceptionHandlerMethod(
            EntityNotFoundException exception
    ) {
        log.error(String.format("%s", exception));
        ExceptionCode exceptionCode = exception.getExceptionCode();
        ProblemDetail problemDetail = ProblemDetail.forStatus(exceptionCode.getHttpStatus());
        problemDetail.setDetail(
                !exception.getMessage().isEmpty() ?  exception.getMessage() : exceptionCode.getDetail()
        );
        problemDetail.setTitle(exceptionCode.getTitle());
        return  ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> exceptionHandlerMethod(
            Exception exception
    ) {
        log.error(String.format("%s", exception));
        ExceptionCode exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        ProblemDetail problemDetail = ProblemDetail.forStatus(exceptionCode.getHttpStatus());
        problemDetail.setDetail(
                !exception.getMessage().isEmpty() ?  exception.getMessage() : exceptionCode.getDetail()
        );
        problemDetail.setTitle(exceptionCode.getTitle());

        return  ResponseEntity.of(problemDetail).build();
    }

}
