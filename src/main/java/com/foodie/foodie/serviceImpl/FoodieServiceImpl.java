package com.foodie.foodie.serviceImpl;

import com.foodie.foodie.dto.*;
import com.foodie.foodie.entity.*;
import com.foodie.foodie.repository.*;
import com.foodie.foodie.service.FoodieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodieServiceImpl implements FoodieService {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private FoodieDAO foodieDAO;

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Autowired
    private ItemOneDAO itemOneDAO;

    @Autowired
    private ItemTwoDAO itemTwoDAO;


    @Override
    public ResponseEntity<BaseResponse> selectItems(FoodieItemRequest foodieItemRequest) {
        Item item = new Item();
        BaseResponse baseResponse = new BaseResponse();
        item.setItemName(foodieItemRequest.getItemName());
        item.setItemPrice(foodieItemRequest.getItemPrice());



        itemDAO.save(item);


        baseResponse.setMessage("Item added successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<BaseResponse> orderDetails(OrderRequest orderRequest) {

        BaseResponse baseResponse = new BaseResponse();
        CartResponse cartResponse = new CartResponse();
        List<Item> allItem = itemDAO.findAll();
        double totalCartValue = allItem.get(0).getTotalPrice();

        for (OrderDetailsRequest order : orderRequest.getOrderDetail()) {
//            if( order.getItemPrice()){
            Item items = new Item();
            items.setItemName(order.getItemName());
            items.setItemPrice(order.getItemPrice());

            items.setTotalPrice(totalCartValue);
            itemDAO.save(items);

            totalCartValue = totalCartValue + order.getItemPrice();
//            }
        }
        cartResponse.setTotalCartValue(totalCartValue);
        baseResponse.setResponse(cartResponse);


        for (Item item : itemDAO.findAll()) {
            item.setTotalPrice(totalCartValue);
            itemDAO.save(item);
        }

        baseResponse.setMessage("fetching a details is  successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @Override
    public TotalCartResponse totalCart(int itemId) {


        Optional<Item> items = itemDAO.findById(itemId);
        Item item = items.get();

        TotalCartResponse totalCartResponse = new TotalCartResponse();
        totalCartResponse.setTotalPrice(item.getTotalPrice());


        return totalCartResponse;
    }

    @Override
    public ResponseEntity<BaseResponse> registerCustomer(FoodieCustomerRequest foodieCustomerRequest) {


        Foodie foodie = new Foodie();
        BaseResponse baseResponse = new BaseResponse();

        foodie.setFirstName(foodieCustomerRequest.getFirstName());
        foodie.setLastName(foodieCustomerRequest.getLastName());
        foodie.setEmail(foodieCustomerRequest.getEmail());
        foodie.setPassword(foodieCustomerRequest.getPassword());
        foodie.setConfirmPassword(foodieCustomerRequest.getConfirmPassword());
        foodie.setPhoneNumber(foodieCustomerRequest.getPhoneNumber());
        foodieDAO.save(foodie);

        baseResponse.setMessage("Customer registered successfully!");
        baseResponse.setHttpStatus(HttpStatus.OK);

        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> loginCustomer(LoginRequest loginRequest) {
        Optional<Foodie> foodie = Optional.ofNullable(foodieDAO.loginCustomer(loginRequest.getEmail(), loginRequest.getPassword()));
        BaseResponse baseResponse = new BaseResponse();

        if (!foodie.isPresent()) {
            baseResponse.setMessage("Customer is not found!");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);

        }
        baseResponse.setMessage("Logged in Successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @Override
    public RestaurantListResponse restaurantDetails() throws IOException {


        List<Restaurant> restaurants = restaurantDAO.findAll();

        RestaurantListResponse restaurantListResponse = new RestaurantListResponse();

        List<RestaurantResponse> restaurantResponseList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantResponse restaurantResponse = new RestaurantResponse();
            restaurantResponse.setRestaurantName(restaurant.getRestaurantName());
            restaurantResponse.setRestaurantCuisine(restaurant.getRestaurantCuisine());

            File file = new File("restaurant-photos/" + restaurant.getRestaurantImagePath());
            Resource fileSystemResource = new FileSystemResource(file);
            restaurantResponse.setRestaurantImage(fileSystemResource.getFile());

            restaurantResponseList.add(restaurantResponse);

        }
        restaurantListResponse.setRestaurantList(restaurantResponseList);

        return restaurantListResponse;
    }

    @Override
    public RestaurantImageResponse getRestaurantPic(int restaurantId) throws IOException {
        RestaurantImageResponse restaurantImageResponse = new RestaurantImageResponse();


        Optional<Restaurant> restaurant = restaurantDAO.findById(restaurantId);
        if (!restaurant.isPresent()) {
            restaurantImageResponse.setMessage("No Image Found");

        }

        Restaurant restaurantPic = restaurant.get();

        File file = new File("restaurant-photos/" + restaurantPic.getRestaurantImagePath());
        Resource fileSystemResource = new FileSystemResource(file);

        restaurantImageResponse.setRestaurantPic(fileSystemResource.getFile());
        restaurantImageResponse.setMessage("Restaurant Image" + restaurantPic.getRestaurantName());
        return restaurantImageResponse;


    }

    @Override
    public ItemOneCardListResponse itemOneCard() throws IOException {


        List<ItemOne> itemOne = itemOneDAO.findAll();
        ItemOneCardListResponse itemOneCardListResponse1 = new ItemOneCardListResponse();
        List<ItemOneCardResponse> itemOneCardResponseList = new ArrayList<>();

        for (ItemOne item1 : itemOne) {
            ItemOneCardResponse itemOneCardResponse1 = new ItemOneCardResponse();
            itemOneCardResponse1.setItemOneName(item1.getItemOneName());
            itemOneCardResponse1.setItemOnePrice(item1.getItemOnePrice());


            File file = new File("itemOne-photos/" + item1.getItemOneImagePath());
            Resource fileSystemResource = new FileSystemResource(file);
            itemOneCardResponse1.setItemImageOne(fileSystemResource.getFile());

            itemOneCardResponseList.add(itemOneCardResponse1);

        }
        itemOneCardListResponse1.setItemOneCardResponseList(itemOneCardResponseList);

  return itemOneCardListResponse1;
    }

    @Override
    public ItemTwoCardListResponse itemTwoCard() throws IOException {
        List<ItemTwo> itemTwo = itemTwoDAO.findAll();

        ItemTwoCardListResponse itemTwoCardResponse = new ItemTwoCardListResponse();

        List<ItemTwoCardResponse> itemTwoCardResponseList = new ArrayList<>();

        for (ItemTwo item2 : itemTwo) {
            ItemTwoCardResponse itemTwoCardResponse1 = new ItemTwoCardResponse();
            itemTwoCardResponse1.setItemTwoName(item2.getItemTwoName());
            itemTwoCardResponse1.setItemTwoPrice(item2.getItemTwoPrice());


            File file = new File("itemTwo-photos/" + item2.getItemTwoImagePath());
            Resource fileSystemResource = new FileSystemResource(file);
            itemTwoCardResponse1.setItemImageTwo(fileSystemResource.getFile());

            itemTwoCardResponseList.add(itemTwoCardResponse1);

        }
        itemTwoCardResponse.setItemTwoCardResponseList(itemTwoCardResponseList);


        return itemTwoCardResponse;

    }

    @Override
    public ResponseEntity<BaseResponse> addItems(ItemOneCardResponse itemOneCardResponse) {


        BaseResponse baseResponse = new BaseResponse();
        ItemOne itemOne = new ItemOne();

        itemOne.setItemOneName(itemOneCardResponse.getItemOneName());
        itemOne.setItemOnePrice(itemOneCardResponse.getItemOnePrice());



        itemOneDAO.save(itemOne);

        baseResponse.setMessage("item added successfully!");
        baseResponse.setHttpStatus(HttpStatus.OK);

        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);



    }

/*    @Override
    public ResponseEntity<BaseResponse> restaurantDetails(int restaurantId) {
        BaseResponse baseResponse = new BaseResponse();
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        Optional<Restaurant> restaurant = restaurantDAO.findById(restaurantId);

        if (!restaurant.isPresent()) {
            baseResponse.setMessage("User Id not found");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);

            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.NOT_FOUND);
        }

      Restaurant restaurant1=restaurant.get();

        restaurantResponse.setRestaurantName(restaurant1.getRestaurantName());
        restaurantResponse.setRestaurantCuisine(restaurant1.getRestaurantCuisine());

        baseResponse.setMessage("User Id found");
        baseResponse.setHttpStatus(HttpStatus.OK);

        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(restaurantResponse);
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);


    }  */

 /*   @Override
    public ResponseEntity<BaseResponse> loginCustomer(LoginResponse loginResponse) {

            BaseResponse baseResponse = new BaseResponse();
           LoginResponse login = new LoginResponse();
           Foodie foodie = foodieDAO.findByIdAndName(loginResponse.getEmail(),loginResponse.getPassword());

            login.setEmail(foodie.getEmail());
            login.setPassword(foodie.getPassword());
            baseResponse.setMessage("Customer logged in successfully!");
            baseResponse.setHttpStatus(HttpStatus.OK);

            baseResponse.setHttpStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
        }  */


}

