package com.wipro.fooddeliveryapp.order.entitys;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid;
	private String rname;
	private String raddress;

}
