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
public class RestaurantResponse {
    private String restaurantName;
    private String restaurantCuisine;
    private File restaurantImage;

}
