package com.foodie.foodie.controller;

import com.foodie.foodie.dto.*;
import com.foodie.foodie.service.FoodieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class FoodieController {

    @Autowired
    private FoodieService foodieService;

    @PostMapping("register-customer")
    public ResponseEntity<BaseResponse> registerCustomer(@RequestBody FoodieCustomerRequest foodieCustomerRequest) {
        if (foodieCustomerRequest == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Customer  cannot be 0 or null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return foodieService.registerCustomer(foodieCustomerRequest);
    }

    @PostMapping("login-customer")
    public ResponseEntity<BaseResponse> loginCustomer(@RequestBody LoginRequest loginRequest) {
        log.info("login sucesssfully", loginRequest);

        if (loginRequest == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Customer ID cannot be 0 or null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return foodieService.loginCustomer(loginRequest);
    }






@PostMapping("select-items")
    public ResponseEntity<BaseResponse> selectItems(@RequestBody FoodieItemRequest foodieItemRequest){

    if(foodieItemRequest == null )   {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Item  cannot be 0 or null");
        baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }
    return foodieService.selectItems(foodieItemRequest);
}

  @GetMapping("order-details")
  public ResponseEntity<BaseResponse> orderDetails(@RequestBody OrderRequest orderRequest){
      if(orderRequest == null )   {
          BaseResponse baseResponse = new BaseResponse();
          baseResponse.setMessage("Item should be a empty");
          baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
          baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
          return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
      }
    return foodieService.orderDetails(orderRequest);
  }

@GetMapping("total-price/{itemId}")
public  TotalCartResponse totalCart(@PathVariable int itemId ){

    return foodieService.totalCart(itemId);

}

@GetMapping("fetch-restaurant")
   public RestaurantListResponse restaurantDetails() throws IOException {
    return foodieService.restaurantDetails();

}

@GetMapping("get-restaurant-image/{restaurantId}")
public RestaurantImageResponse getRestaurantPic(@PathVariable int restaurantId) throws IOException {

    return  foodieService.getRestaurantPic(restaurantId);
}




@GetMapping("get-restaurant-one")
    public ItemOneCardListResponse itemOneCard() throws IOException {

    return foodieService.itemOneCard();

}

    @GetMapping("get-restaurant-two")
    public ItemTwoCardListResponse itemTwoCard() throws IOException {
        return foodieService.itemTwoCard();

    }




    @PostMapping("add-items")
    public ResponseEntity<BaseResponse> addItems(@RequestBody ItemOneCardResponse itemOneCardResponse) {
        if (itemOneCardResponse == null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Customer  cannot be 0 or null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return foodieService.addItems(itemOneCardResponse);
    }

}
