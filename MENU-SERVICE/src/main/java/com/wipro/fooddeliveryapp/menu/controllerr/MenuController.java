package com.wipro.fooddeliveryapp.menu.controllerr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.wipro.fooddeliveryapp.menu.entity.Menu;
import com.wipro.fooddeliveryapp.menu.entity.RestaurantDTO;
import com.wipro.fooddeliveryapp.menu.servicess.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;

    @PostMapping("/createMenu/{restaurantId}")
    public Menu createMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        logger.info("Creating menu for restaurantId: {}", restaurantId);
        return menuService.createMenu(menu, restaurantId);
    }

    @GetMapping("/getAllMenus")
    public List<Menu> getAllMenus() {
        logger.info("Fetching all menus");
        return menuService.getAllMenus();
    }

    @GetMapping("/getMenuById/{id}")
    public Menu getMenuById(@PathVariable Long id) {
        logger.info("Fetching menu with id: {}", id);
        return menuService.getMenuById(id);
    }

    @GetMapping("/getMenusByRestaurantId/{restaurantId}")
    public List<Menu> getMenusByRestaurantId(@PathVariable Long restaurantId) {
        logger.info("Fetching menus for restaurantId: {}", restaurantId);
        return menuService.getMenusByRestaurantId(restaurantId);
    }

    @GetMapping("/getSelectedMenus/{restaurantId}")
    public List<Menu> selectMenuItems(@PathVariable Long restaurantId, @RequestParam List<String> items) {
        logger.info("Fetching selected items {} for restaurantId: {}", items, restaurantId);
        return menuService.getSelectedMenus(restaurantId, items);
    }

    @PutMapping("/updateMenuById/{id}")
    public Menu updateMenu(@PathVariable Long id, @RequestBody Menu menu) {
        logger.info("Updating menu with id: {}", id);
        return menuService.updateMenu(id, menu);
    }

    @DeleteMapping("/deleteMenuById/{id}")
    public String deleteMenu(@PathVariable Long id) {
        logger.info("Deleting menu with id: {}", id);
        menuService.deleteMenu(id);
        return "Menu with ID " + id + " deleted successfully.";
    }

    @GetMapping("/restaurantById/{id}")
    public RestaurantDTO getRestaurantDetails(@PathVariable Long id) {
        logger.info("Fetching restaurant details for id: {}", id);
        return menuService.getRestaurantById(id);
    }
}
