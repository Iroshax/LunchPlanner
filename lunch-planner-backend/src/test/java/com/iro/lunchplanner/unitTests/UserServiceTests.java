package com.iro.lunchplanner.unitTests;

import com.iro.lunchplanner.dto.LoginDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.User;
import com.iro.lunchplanner.repository.UserRepository;
import com.iro.lunchplanner.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test User service
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldAbleToRegisterUserSuccessfully(){
        UserDto userDto = new UserDto("John", "Doe", "johndoe", "password", "Team1", "john.doe@example.com");
        User user = new User(0,"John", "Doe", "johndoe", "cGFzc3dvcmQ=", "Team1", "john.doe@example.com");

        when(userRepository.findByUserName(userDto.getUserName())).thenReturn(Optional.empty());
        userService.registerUser(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
     void registerUserShouldThrowsException() {

        UserDto userDto = new UserDto("John", "Doe", "johndoe", "password", "Team1", "john.doe@example.com");
        User user = new User(1,"John", "Doe", "johndoe", "cGFzc3dvcmQ=", "Team1", "john.doe@example.com");
        when(userRepository.findByUserName(userDto.getUserName())).thenReturn(Optional.of(user));
        assertThrows(LunchPlanException.class, () -> {userService.registerUser(userDto);
        });
    }

    @Test
    void shouldReturnUserList() {
        User user1 = new User(1,"John", "Doe", "johndoe", "password", "Team1", "john.doe@example.com");
        User user2 = new User(2,"Jane", "Doe", "janedoe", "password", "Team2", "jane.doe@example.com");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);
        List<UserDto> result = userService.getUserList();
        assertEquals(2, result.size());
        assertEquals(user1.getUserName(), result.get(0).getUserName());
        assertEquals(user2.getUserName(), result.get(1).getUserName());
    }

    @Test
    void shouldAbleToLoginWhenUserExists() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("testUser");
        loginDto.setPassword("testPassword");
        User user = new User();
        user.setUserName("testUser");
        user.setPassword(Base64.getEncoder().encodeToString("testPassword".getBytes()));
        when(userRepository.findByUserNamePassword(loginDto.getUserName(), user.getPassword())).thenReturn(Optional.of(user));

        UserDto result = userService.userLogin(loginDto);

        assertNotNull(result);
        assertEquals(user.getUserName(), result.getUserName());
    }

    @Test
    void shouldNotAllowUserLoginWhenUserDoesNotExist() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("testUser");
        loginDto.setPassword("testPassword");
        when(userRepository.findByUserNamePassword(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(LunchPlanException.class, () -> userService.userLogin(loginDto));
    }

    @Test
    void UserLoginShouldThrowsException() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserName("testUser");
        loginDto.setPassword("testPassword");
        when(userRepository.findByUserNamePassword(anyString(), anyString())).thenThrow(new RuntimeException("Test exception"));

        assertThrows(LunchPlanException.class, () -> userService.userLogin(loginDto));
    }
}


