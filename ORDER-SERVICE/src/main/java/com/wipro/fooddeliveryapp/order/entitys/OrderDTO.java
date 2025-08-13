package com.wipro.fooddeliveryapp.order.entitys;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private Long customerId;
    private Long restaurantId;
    private List<Long> menuItemIds;
    private Double totalAmount;
    private String orderStatus;
}
