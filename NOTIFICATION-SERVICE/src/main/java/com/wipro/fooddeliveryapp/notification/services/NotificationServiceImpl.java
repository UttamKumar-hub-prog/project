package com.wipro.fooddeliveryapp.notification.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.fooddeliveryapp.notification.entity.Notification;
import com.wipro.fooddeliveryapp.notification.entity.NotificationDTO;
import com.wipro.fooddeliveryapp.notification.repositorys.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public String sendNotification(NotificationDTO dto) {
        // In real case, integrate with email/SMS provider here

        Notification notification = new Notification();
        notification.setOrderId(dto.getOrderId());
        notification.setCustomerContact(dto.getCustomerContact());
        notification.setMessage(dto.getMessage());
        notification.setStatus("SENT");

        notificationRepository.save(notification);

        return "Notification sent to " + dto.getCustomerContact();
    }
}
