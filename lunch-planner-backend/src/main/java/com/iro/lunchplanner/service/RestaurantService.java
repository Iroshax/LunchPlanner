package com.iro.lunchplanner.service;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.RestaurantSubmitDto;

import java.util.List;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
public interface RestaurantService {

    /**
     * Submit restaurant for the open session
     * @param restaurantSubmitDto restaurantSubmitDto object
     * @return LunchSessionCache
     */
    LunchSessionCache submitRestaurant(RestaurantSubmitDto restaurantSubmitDto);

    /**
     * get restaurant list
     * @return List<RestaurantDto>
     */
    List<RestaurantDto> getRestaurantList();
}
