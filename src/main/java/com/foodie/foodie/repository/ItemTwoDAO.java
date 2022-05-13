package com.foodie.foodie.repository;

import com.foodie.foodie.entity.ItemTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTwoDAO  extends JpaRepository<ItemTwo,Integer> {
}
