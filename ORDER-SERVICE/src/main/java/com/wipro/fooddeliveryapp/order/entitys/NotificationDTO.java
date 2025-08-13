package com.wipro.fooddeliveryapp.order.entitys;


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
