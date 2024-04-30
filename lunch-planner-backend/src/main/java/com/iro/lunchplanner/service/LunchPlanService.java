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

    /**
     * Initiate lunch session
     * @param user userObject
     * @return LunchSessionCache
     */
    LunchSessionCache initiateSession(UserDto user);

    /**
     * Get session list
     * @return List<SessionDto>
     */
    List<SessionDto> getSessionList();

    /**
     *
     * @param user userObject
     * @return RestaurantDto
     */
    RestaurantDto endSession(UserDto user);

    /**
     * get active session with the status = "Open"
     * @return LunchSessionCache
     */
    LunchSessionCache getActiveSession();

    /**
     * get current session's status
     * @return String
     */
    String getSessionStatus();

    /**
     * get latest session info
     * @return SessionDto
     */
    SessionDto getLatestSession();
}
