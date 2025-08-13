package com.wipro.fooddeliveryapp.notification.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.fooddeliveryapp.notification.entity.NotificationDTO;
import com.wipro.fooddeliveryapp.notification.services.NotificationService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String sendNotification(@RequestBody NotificationDTO dto) {
        return notificationService.sendNotification(dto);
    }
}
