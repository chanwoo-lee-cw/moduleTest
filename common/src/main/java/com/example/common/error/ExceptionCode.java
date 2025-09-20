package com.example.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.net.URI;

@Getter
public enum ExceptionCode {
    // 400 ERROR
    BAD_REQUEST("BAD REQUEST", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // 404 ERROR
    NOT_FIND_ENTITY("NOT_FIND_ENTITY", HttpStatus.NOT_FOUND, "DB의 해당하는 데이터가 존재하지 않습니다."),

    // 500 ERROR
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "요청을 처리 중 문제가 발생했습니다."),
    ;

    private final String title;
    private final HttpStatus httpStatus;
    private final String detail;
    private final URI type;

    ExceptionCode(
            String title,
            HttpStatus httpStatus,
            String detail,
            URI type
    ) {
        this.title = title;
        this.httpStatus = httpStatus;
        this.detail = detail;
        this.type = type;
    }


    ExceptionCode(
            String title,
            HttpStatus httpStatus,
            String detail
    ) {
        this(title, httpStatus, detail, URI.create("about:blank"));
    }
}
