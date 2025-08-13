package com.wipro.fooddeliveryapp.menu.servicess;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.fooddeliveryapp.menu.entity.Menu;
import com.wipro.fooddeliveryapp.menu.entity.RestaurantDTO;
import com.wipro.fooddeliveryapp.menu.exception.MenuNotFoundException;
import com.wipro.fooddeliveryapp.menu.exception.RestaurantNotFoundException;
import com.wipro.fooddeliveryapp.menu.feign.RestuarantClient;
import com.wipro.fooddeliveryapp.menu.repositorys.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
	private final MenuRepository menuRepository;
	private final RestuarantClient restaurantClient;

	public Menu createMenu(Menu menu, Long restaurantId) {
		RestaurantDTO restaurant = restaurantClient.getRestaurantById(restaurantId);
		if (restaurant == null) {
			throw new MenuNotFoundException("Restaurant not found");
		}
		menu.setRestaurantId(restaurantId);
		return menuRepository.save(menu);
	}

	public List<Menu> getAllMenus() {
		return menuRepository.findAll();
	}

	public Menu getMenuById(Long id) {
		return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found with ID: " + id));
	}

	public List<Menu> getMenusByRestaurantId(Long restaurantId) {
		boolean restaurantExists = menuRepository.existsById(restaurantId);
	    if (!restaurantExists) {
	        throw new RestaurantNotFoundException("Restaurant with ID " + restaurantId + " not found");
	    }

	    
	    List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
	    if (menus.isEmpty()) {
	        throw new MenuNotFoundException("No menus found for Restaurant ID " + restaurantId);
	    }

	    return menus;
	}

	public List<Menu> getSelectedMenus(Long restaurantId, List<String> itemNames) {
		return menuRepository.findByRestaurantIdAndItemNameIn(restaurantId, itemNames);
	}

	public Menu updateMenu(Long id, Menu updatedMenu) {
		Menu existing = getMenuById(id);

		existing.setItemName(updatedMenu.getItemName());
		existing.setPrice(updatedMenu.getPrice());
		existing.setRestaurantId(updatedMenu.getRestaurantId());

		return menuRepository.save(existing);
	}

	public void deleteMenu(Long id) {
		Menu existing = getMenuById(id);
		menuRepository.delete(existing);
	}

	@Override
	public RestaurantDTO getRestaurantById(Long id) {
		return restaurantClient.getRestaurantById(id);
	}
	
	 
}
