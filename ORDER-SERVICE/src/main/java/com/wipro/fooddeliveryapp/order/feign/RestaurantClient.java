package com.wipro.fooddeliveryapp.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.fooddeliveryapp.order.entitys.RestaurantDTO;

@FeignClient(name = "restaurant-service", url = "http://localhost:8080/api/v1/restuarant")
public interface RestaurantClient {

    @GetMapping("/getById/{id}")
    RestaurantDTO getRestaurantById(@PathVariable("id") Long id);
}