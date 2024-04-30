package com.iro.lunchplanner.service;

import com.iro.lunchplanner.dto.LoginDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.User;
import com.iro.lunchplanner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto userLogin(LoginDto loginDto) throws LunchPlanException{

        Optional<User> optionalUser;
        try {
            optionalUser = userRepository.findByUserNamePassword(loginDto.getUserName(),
                    Base64.getEncoder().encodeToString(loginDto.getPassword().getBytes(StandardCharsets.UTF_8)));
             if(optionalUser.isPresent()){
                 User user = optionalUser.get();
                 return new UserDto(user.getFirstName(),user.getLastName(),user.getUserName(),user.getPassword(),
                         user.getTeamName(),user.getEmail());
             }else{
                 throw new LunchPlanException("User not found. Please check the credentials.");
             }

        }catch(Exception e){
            throw new LunchPlanException("Error occurred during the user login: "+e.getMessage());
        }
    }

    @Override
    public void registerUser(UserDto userDto) throws LunchPlanException{

        User user = new User();
        try {

            Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
            if(optionalUser.isPresent()){
                throw new LunchPlanException("Username already exists.");
            }else{
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setUserName(userDto.getUserName());
                user.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes(StandardCharsets.UTF_8)));
                user.setTeamName(userDto.getTeamName());
                user.setEmail(userDto.getEmail());
                userRepository.save(user);
            }

        }catch(Exception e){
            throw new LunchPlanException("Error occurred while registering the user: "+e.getMessage());
        }
    }

    @Override
    public List<UserDto> getUserList() throws LunchPlanException{

        List<User> userList;
        List<UserDto>userDtoList;
        try {
            userList = userRepository.findAll();
            if(userList.isEmpty()){
                return new ArrayList<>();
            }else{
                userDtoList = userListToUserDto(userList);
            }
        }catch(Exception e){
            throw new LunchPlanException("Error occurred while fetching the user list: "+e.getMessage());
        }
        return userDtoList;
    }

    /**
     * Map user list to userDto list
     * @param userList userList
     * @return List<UserDto>
     */
    private List<UserDto> userListToUserDto(List<User> userList) {

        UserDto userDto = null;
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user:userList){
            userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setUserName(user.getUserName());
            userDto.setPassword((user.getPassword()));
            userDto.setTeamName(user.getTeamName());
            userDto.setEmail(user.getEmail());
            userDtoList.add(userDto);

        }
        return userDtoList;
    }
}
