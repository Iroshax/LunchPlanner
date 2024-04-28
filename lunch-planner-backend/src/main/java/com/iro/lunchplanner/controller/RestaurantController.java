package com.iro.lunchplanner.controller;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.RestaurantSubmitDto;
import com.iro.lunchplanner.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/lunchPlanner")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/submit")
    public Map<String,RestaurantDto> submitRestaurant(@RequestBody RestaurantSubmitDto restaurantSubmitDto){
        return restaurantService.submitRestaurant(restaurantSubmitDto).getSessionCache();
    }

    @PostMapping("/restaurant")
    public List<RestaurantDto> getRestaurantList(){
        return restaurantService.getRestaurantList();
    }
}
