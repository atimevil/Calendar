package com.sparta.calendar.api_swagger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(Result.OK.getCode(), Result.OK.getMessage(), data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(Result.OK.getCode(), Result.OK.getMessage(), null);
    }
}
