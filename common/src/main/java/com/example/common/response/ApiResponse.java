package com.example.common.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {
    @NonNull
    private Integer status;
    private T data;

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(
                HttpStatus.OK.value()
        );
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                data
        );
    }

}
