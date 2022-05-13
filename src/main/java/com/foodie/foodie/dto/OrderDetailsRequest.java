package com.foodie.foodie.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderDetailsRequest {
    private String itemName;
    private double itemPrice;

}
