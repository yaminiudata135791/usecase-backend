package com.foodie.foodie.repository;

import com.foodie.foodie.entity.ItemOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOneDAO extends JpaRepository<ItemOne,Integer> {

}
