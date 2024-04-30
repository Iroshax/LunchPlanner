package com.iro.lunchplanner.unitTests;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.constants.LunchPlanConstants;
import com.iro.lunchplanner.dto.RestaurantSubmitDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.Session;
import com.iro.lunchplanner.repository.SessionRepository;
import com.iro.lunchplanner.service.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test restaurant submissions
 */
@ExtendWith(MockitoExtension.class)
class RestaurantServiceTests {


    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private LunchSessionCache lunchSessionCache;


    @InjectMocks
    private RestaurantServiceImpl restaurantServiceImpl;


    @Test
    void shouldSubmitRestaurantSuccessfully() {

        RestaurantSubmitDto restaurantSubmitDto = new RestaurantSubmitDto();
        UserDto user = new UserDto();
        user.setUserName("john");
        restaurantSubmitDto.setUser(user);
        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenReturn(Optional.of(new Session()));
        when(lunchSessionCache.containsKey(user.getUserName())).thenReturn(false);

        LunchSessionCache result = restaurantServiceImpl.submitRestaurant(restaurantSubmitDto);

        assertNotNull(result);
        verify(lunchSessionCache, times(1)).put(user.getUserName(), restaurantSubmitDto.getRestaurant());
    }

    @Test
    void shouldNotAllowSubmitMultipleRestaurants() {

        RestaurantSubmitDto restaurantSubmitDto = new RestaurantSubmitDto();
        UserDto user = new UserDto();
        user.setUserName("john");
        restaurantSubmitDto.setUser(user);
        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenReturn(Optional.of(new Session()));
        when(lunchSessionCache.containsKey(user.getUserName())).thenReturn(true);

        assertThrows(LunchPlanException.class, () -> restaurantServiceImpl.submitRestaurant(restaurantSubmitDto));
    }

    @Test
     void shouldNotAllowSubmitRestaurantWhenSessionClosed() {
        RestaurantSubmitDto restaurantSubmitDto = new RestaurantSubmitDto();
        UserDto user = new UserDto();
        user.setUserName("john");
        restaurantSubmitDto.setUser(user);
        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenReturn(Optional.empty());

        assertThrows(LunchPlanException.class, () -> restaurantServiceImpl.submitRestaurant(restaurantSubmitDto));
    }
}

