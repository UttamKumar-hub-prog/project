package com.wipro.fooddeliveryapp.restuarant.controlller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.fooddeliveryapp.restuarant.entitys.Restuarant;
import com.wipro.fooddeliveryapp.restuarant.servicess.RestuarantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restuarant")
public class RestuarantController {
	private final RestuarantService restuarantService;

	@PostMapping("/savedata")
	public Restuarant saveData(@RequestBody Restuarant restaurant) {
		return restuarantService.saveData(restaurant);
	}

	@GetMapping("/getAll")
	public List<Restuarant> getAll() {
		return restuarantService.getAll();

	}

	@GetMapping("/getById/{id}")
	public Restuarant getById(@PathVariable Long id) {
		return restuarantService.getRestaurantById(id);
	}

	@PutMapping("/updateById/{id}")
	public Restuarant update(@PathVariable Long id, @RequestBody Restuarant restaurant) {
		return restuarantService.updateRestaurant(id, restaurant);
	}
	
	@PatchMapping("/PatchUpdateById/{id}")
    public Restuarant partialUpdate(@PathVariable Long id, @RequestBody Restuarant restaurant) {
        return restuarantService.patchUpdateById(id, restaurant);
    }

	@DeleteMapping("/deleteById/{id}")
	public String delete(@PathVariable Long id) {
		restuarantService.deleteRestaurant(id);
		return "Restaurant with ID " + id + " deleted successfully.";
	}
	
 
	}
























 