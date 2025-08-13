package com.wipro.fooddeliveryapp.order.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.fooddeliveryapp.order.entitys.MenuDTO;

@FeignClient(name = "MENU-SERVICE", path = "/api/v1/menu")
public interface MenuClient {
	@GetMapping("/menus/restaurant/{restaurantId}")
    List<MenuDTO> getMenusByRestaurantId(@PathVariable Long restaurantId);}
