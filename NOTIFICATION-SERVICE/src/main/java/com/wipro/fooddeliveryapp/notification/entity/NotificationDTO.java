package com.wipro.fooddeliveryapp.notification.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long orderId;
    private String customerContact;
    private String message;
}
