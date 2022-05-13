package com.foodie.foodie.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "restaurant")
public class Restaurant  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private  int restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_cuisine")
    private String restaurantCuisine;

    @Column(name = "restaurant_image_path")
    private String restaurantImagePath;

}
