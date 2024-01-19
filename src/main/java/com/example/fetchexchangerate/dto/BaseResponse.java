package com.example.fetchexchangerate.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private ErrorResponse error;
    private T currency;

    public BaseResponse(ErrorResponse error, T currency) {
        this.error = error;
        this.currency = currency;
    }
}
