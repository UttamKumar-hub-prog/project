package com.wipro.fooddeliveryapp.order.entitys;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NotBlank(message = "Customer ID cannot be null")
    private Long customerId;  
    @NotBlank(message = "Restaurant ID cannot be null")
    private Long restaurantId;
    @NotEmpty(message = "Menu item list cannot be empty")
    @ElementCollection
    private List<Long> menuItemIds;  
    
    @Positive(message = "Total amount must be greater than zero")
    private Double totalAmount;   
    private String orderStatus;   


}
