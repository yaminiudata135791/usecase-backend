package com.foodie.foodie.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class ItemOneImageResponse {

    private File itemImageOne;
    private String message;
}
