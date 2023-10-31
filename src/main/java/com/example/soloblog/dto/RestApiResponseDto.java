package com.example.soloblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestApiResponseDto {
    private int status;
    private String message;
    private Object result;

    public RestApiResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
