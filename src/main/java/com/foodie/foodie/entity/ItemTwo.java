package com.foodie.foodie.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_two")
public class ItemTwo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_two_id")
    private int itemTwoId;


    @Column(name = "item_two_name")
    private String itemTwoName;

    @Column(name = "item_two_price")
    private double itemTwoPrice;

    @Column(name = "item_two_image_path")
    private String itemTwoImagePath;
}
