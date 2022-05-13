package com.foodie.foodie.repository;

import com.foodie.foodie.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDAO extends JpaRepository<Item,Integer> {
    @Query(value="SELECT * FROM items",nativeQuery = true)
    List<Item> findAll();


}
