package com.foodie.foodie.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RestaurantListResponse {
    private List<RestaurantResponse> restaurantList;
}
