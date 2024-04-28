package com.iro.lunchplanner.service;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.SessionDto;
import com.iro.lunchplanner.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
public interface LunchPlanService {

    LunchSessionCache initiateSession(UserDto user);

    List<SessionDto> getSessionList();

    RestaurantDto endSession(UserDto user);

    LunchSessionCache getActiveSession();

    String getSessionStatus();

    SessionDto getLatestSession();
}
