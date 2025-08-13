package com.wipro.fooddeliveryapp.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.fooddeliveryapp.order.entitys.NotificationDTO;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationClient {

	@PostMapping("/notifications")
	void sendNotification(@RequestBody NotificationDTO dto);
}
