package com.foodie.foodie.service;

import com.foodie.foodie.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface FoodieService {
    ResponseEntity<BaseResponse> selectItems(FoodieItemRequest foodieItemRequest);

    ResponseEntity<BaseResponse> orderDetails(OrderRequest orderRequest);


    TotalCartResponse totalCart(int itemId);

    ResponseEntity<BaseResponse> registerCustomer(FoodieCustomerRequest foodieCustomerRequest);

    ResponseEntity<BaseResponse> loginCustomer(LoginRequest loginRequest);


    RestaurantListResponse restaurantDetails() throws IOException;

    RestaurantImageResponse getRestaurantPic(int restaurantId) throws IOException;

     ItemOneCardListResponse itemOneCard() throws IOException;

    ItemTwoCardListResponse itemTwoCard() throws IOException;

    ResponseEntity<BaseResponse> addItems(ItemOneCardResponse itemOneCardResponse);
}
