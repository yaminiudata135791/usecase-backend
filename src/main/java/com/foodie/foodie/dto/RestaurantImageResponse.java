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
public class RestaurantImageResponse {

    private File restaurantPic;
    private String message;
}
