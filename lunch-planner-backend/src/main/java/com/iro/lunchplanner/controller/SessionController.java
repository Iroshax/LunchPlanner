package com.iro.lunchplanner.controller;

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
@RequestMapping("/api/lunchPlanner/session")
public class SessionController {

    private final LunchPlanService lunchPlanService;
    public SessionController(LunchPlanService lunchPlanService) {
        this.lunchPlanService = lunchPlanService;
    }

    @PostMapping("/initiate")
    public Map<String,RestaurantDto> initiateSession(@RequestBody UserDto user){
        return lunchPlanService.initiateSession(user).getSessionCache();
    }

    @PostMapping("/end")
    public RestaurantDto endSession(@RequestBody UserDto user){
        return lunchPlanService.endSession(user);
    }

    @GetMapping()
    public List<SessionDto> getSessionList(){
        return lunchPlanService.getSessionList();
    }

    @GetMapping("/latest")
    public SessionDto getLatestSession(){
        return lunchPlanService.getLatestSession();
    }

    @GetMapping("/status")
    public String getSessionStatus(){
        return lunchPlanService.getSessionStatus();
    }

    @GetMapping("/active")
    public Map<String,RestaurantDto> getActiveSession(){
        return lunchPlanService.getActiveSession().getSessionCache();
    }

}
