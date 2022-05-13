package com.foodie.foodie.repository;

import com.foodie.foodie.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant,Integer> {

    @Query(value="SELECT * FROM restaurant",nativeQuery = true)
    List<Restaurant> getRestaurantDetails();
}
