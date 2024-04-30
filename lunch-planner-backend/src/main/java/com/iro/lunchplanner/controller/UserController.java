package com.iro.lunchplanner.controller;

import com.iro.lunchplanner.dto.LoginDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-28
 */

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/lunchPlanner/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto userLogin(@RequestBody LoginDto loginDto){
        return userService.userLogin(loginDto);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto userDto){
        userService.registerUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUserList(){
        return userService.getUserList();
    }
}
