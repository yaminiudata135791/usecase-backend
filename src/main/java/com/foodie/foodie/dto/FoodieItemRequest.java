package com.foodie.foodie.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FoodieItemRequest {
    private String itemName;
    private double itemPrice;
    private double totalPrice;

}



