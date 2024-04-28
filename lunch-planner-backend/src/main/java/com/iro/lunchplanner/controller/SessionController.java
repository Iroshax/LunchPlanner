package com.iro.lunchplanner.controller;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.SessionDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.service.LunchPlanService;
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
public class SessionController {


    private final LunchPlanService lunchPlanService;

    public SessionController(LunchPlanService lunchPlanService) {
        this.lunchPlanService = lunchPlanService;
    }


    @PostMapping("/session/initiate")
    public Map<String,RestaurantDto> initiateSession(@RequestBody UserDto user){
        return lunchPlanService.initiateSession(user).getSessionCache();
    }

    @PostMapping("/session/end")
    public RestaurantDto endSession(@RequestBody UserDto user){
        return lunchPlanService.endSession(user);
    }

    @GetMapping("/session")
    public List<SessionDto> getSessionList(){
        return lunchPlanService.getSessionList();
    }


    @GetMapping("/session/latest")
    public SessionDto getLatestSession(){
        return lunchPlanService.getLatestSession();
    }

    @GetMapping("/session/status")
    public String getSessionStatus(){
        return lunchPlanService.getSessionStatus();
    }

    @GetMapping("/session/active")
    public Map<String,RestaurantDto> getActiveSession(){
        return lunchPlanService.getActiveSession().getSessionCache();
    }









}
