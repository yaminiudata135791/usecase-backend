package com.foodie.foodie.repository;

import com.foodie.foodie.entity.Foodie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodieDAO  extends JpaRepository<Foodie,Integer> {
    @Query(value="SELECT * from registration where email=?1 and password=?2", nativeQuery = true)
     Foodie loginCustomer(String email,String password);

    //    Foodie findByIdAndName(String email,String password);
}
