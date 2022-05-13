package com.foodie.foodie.dto;


import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class BaseResponse {
    private String message;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private Object response;
}
