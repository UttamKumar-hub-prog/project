package com.wipro.fooddeliveryapp.menu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.fooddeliveryapp.menu.entity.RestaurantDTO;

@FeignClient(name = "RESTUARANT-SERVICE", path = "/api/v1/restuarant")
public interface RestuarantClient {

	@GetMapping("/getById/{id}")
	RestaurantDTO getRestaurantById(@PathVariable Long id);
}