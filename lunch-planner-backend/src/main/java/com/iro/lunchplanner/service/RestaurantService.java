package com.iro.lunchplanner.service;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.RestaurantSubmitDto;

import java.util.List;
import java.util.Map;

public interface RestaurantService {
    LunchSessionCache submitRestaurant(RestaurantSubmitDto restaurantSubmitDto);

    List<RestaurantDto> getRestaurantList();
}
