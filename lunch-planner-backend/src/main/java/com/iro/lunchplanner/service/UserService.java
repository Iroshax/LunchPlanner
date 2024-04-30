package com.iro.lunchplanner.service;

import com.iro.lunchplanner.dto.LoginDto;
import com.iro.lunchplanner.dto.UserDto;

import java.util.List;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-28
 */
public interface UserService {

    /**
     * User login by username and password
     * @param loginDto login details
     * @return UserDto
     */
    UserDto userLogin(LoginDto loginDto);

    /**
     * Register new user
     * @param userDto  New User details
     */
    void registerUser(UserDto userDto);

    /**
     * Return all users as a List
     * @return List<UserDto>
     */
    List<UserDto> getUserList();
}
