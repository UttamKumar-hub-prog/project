package com.wipro.fooddeliveryapp.order.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

 
	private Long rid;
	private String rname;
	private String raddress;

}
