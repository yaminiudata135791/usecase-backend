package com.foodie.foodie.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_one")
public class ItemOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_one_id")
    private int itemOneId;


    @Column(name = "item_one_name")
    private String itemOneName;

    @Column(name = "item_one_price")
    private double itemOnePrice;

    @Column(name = "item_one_image_path")
    private String itemOneImagePath;

}
