package com.wipro.fooddeliveryapp.notification.services;

import com.wipro.fooddeliveryapp.notification.entity.NotificationDTO;

public interface NotificationService {

	String sendNotification(NotificationDTO dto);

}
