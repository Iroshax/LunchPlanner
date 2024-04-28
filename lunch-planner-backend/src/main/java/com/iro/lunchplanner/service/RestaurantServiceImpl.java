package com.iro.lunchplanner.service;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.RestaurantSubmitDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.Session;
import com.iro.lunchplanner.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final LunchSessionCache lunchSessionCache;
    private final SessionRepository sessionRepository;

    public RestaurantServiceImpl(LunchSessionCache lunchSessionCache, SessionRepository sessionRepository) {
        this.lunchSessionCache = lunchSessionCache;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public LunchSessionCache submitRestaurant(RestaurantSubmitDto restaurantSubmitDto) throws LunchPlanException {

        Optional<Session> openSession = sessionRepository.getOpenSessionByStatus("Open");
        if(openSession.isPresent()){
            if(!lunchSessionCache.containsKey(restaurantSubmitDto.getUser().getUserName())){
                lunchSessionCache.put(restaurantSubmitDto.getUser().getUserName(),restaurantSubmitDto.getRestaurant());
            }else{
                throw new LunchPlanException("You have already submitted a restaurant");
            }

        }else{
            throw new LunchPlanException("No Open Session Found for the day");

        }
        return lunchSessionCache;
    }

    @Override
    public List<RestaurantDto> getRestaurantList() {
        return List.of();
    }
}
