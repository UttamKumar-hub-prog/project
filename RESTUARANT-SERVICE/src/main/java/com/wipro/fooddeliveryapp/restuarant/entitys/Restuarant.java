package com.wipro.fooddeliveryapp.restuarant.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restuarant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rid;

	@NotBlank(message = "Restaurant name is required")
	private String rname;
	@NotBlank(message = "Restaurant address is required")
	private String raddress;

	@NotBlank(message = "Type is required")

	private String type;

}
