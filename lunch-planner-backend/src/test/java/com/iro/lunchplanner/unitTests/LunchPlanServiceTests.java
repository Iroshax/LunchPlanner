package com.iro.lunchplanner.unitTests;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.constants.LunchPlanConstants;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.Session;
import com.iro.lunchplanner.repository.SessionRepository;
import com.iro.lunchplanner.service.LunchPlanServiceImpl;
import com.iro.lunchplanner.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LunchPlanServiceTests {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private LunchSessionCache lunchSessionCache;

    @Mock
    private SendMailService sendMailService;

    @InjectMocks
    private LunchPlanServiceImpl lunchPlanService;

    /**
     Test initiateSession method
     */
    @Test
    void shouldInitiateSessionWhenNoActiveSessions() {
        UserDto user = new UserDto();
        user.setUserName("john");
        user.setEmail("john@outllok.com");
        when(sessionRepository.checkOpenSessions(anyString(), anyString())).thenReturn(Collections.emptyList());

        LunchSessionCache result = lunchPlanService.initiateSession(user);

        assertNotNull(result);
        verify(sessionRepository, times(1)).save(any(Session.class));
        verify(sendMailService, times(1)).sendMail(anyString(), eq(user.getEmail()));
    }

    @Test
    void shouldNotAllowInitiateSessionWhenActiveSessionExists() {
        UserDto user = new UserDto();
        user.setUserName("john");
        when(sessionRepository.checkOpenSessions(anyString(), anyString())).thenReturn(List.of(new Session()));

        assertThrows(LunchPlanException.class, () -> lunchPlanService.initiateSession(user));
    }

    @Test
    void initiateSessionShouldThrowsException() {
        UserDto user = new UserDto();
        user.setUserName("john");
        when(sessionRepository.checkOpenSessions(anyString(), anyString())).thenThrow(new LunchPlanException("Error occurred."));

        assertThrows(LunchPlanException.class, () -> lunchPlanService.initiateSession(user));
    }
    /**
     Test getSessionStatus method
     */
    @Test
     void getSessionStatusWhenOpen() {
        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenReturn(Optional.of(new Session()));
        String result = lunchPlanService.getSessionStatus();
        assertEquals(LunchPlanConstants.OPEN, result);
        verify(sessionRepository, times(1)).getOpenSessionByStatus(LunchPlanConstants.OPEN);
    }

    @Test
     void getSessionStatusWhenClosed() {

        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenReturn(Optional.empty());
        String result = lunchPlanService.getSessionStatus();
        assertEquals(LunchPlanConstants.CLOSED, result);
        verify(sessionRepository, times(1)).getOpenSessionByStatus(LunchPlanConstants.OPEN);
    }

    @Test
     void getSessionStatusShouldThrowsException() {

        when(sessionRepository.getOpenSessionByStatus(LunchPlanConstants.OPEN)).thenThrow(new LunchPlanException("Error occurred"));
        assertThrows(LunchPlanException.class, () -> lunchPlanService.getSessionStatus());
        verify(sessionRepository, times(1)).getOpenSessionByStatus(LunchPlanConstants.OPEN);
    }

    /**
     Test endSession method
     */

    //@Test
    void shouldEndSessionWhenStatusIsOpen() {
        UserDto user = new UserDto();
        user.setUserName("testUser");
        Session session = new Session();
        session.setSessionStatus(LunchPlanConstants.OPEN);
        when(sessionRepository.getOpenSession(user.getUserName(), LunchPlanConstants.OPEN)).thenReturn(Optional.of(session));
        when(lunchPlanService.selectRandomRestaurant()).thenReturn(new RestaurantDto());

        RestaurantDto result = lunchPlanService.endSession(user);

        assertNotNull(result);
        assertEquals(LunchPlanConstants.CLOSED, session.getSessionStatus());
        verify(sessionRepository, times(1)).save(session);
        verify(lunchSessionCache, times(1)).clearLunchSessionCache();
    }

    @Test
    void shouldAllowEndSessionWhenNoOpenSession() {
        UserDto user = new UserDto();
        user.setUserName("john");
        when(sessionRepository.getOpenSession(user.getUserName(), LunchPlanConstants.OPEN)).thenReturn(Optional.empty());

        assertThrows(LunchPlanException.class, () -> lunchPlanService.endSession(user));
    }

    @Test
    void endSessionShouldThrowsException() {
        UserDto user = new UserDto();
        user.setUserName("john");
        when(sessionRepository.getOpenSession(user.getUserName(), LunchPlanConstants.OPEN)).thenThrow(new LunchPlanException("Error occurred"));
        assertThrows(LunchPlanException.class, () -> lunchPlanService.endSession(user));
    }
}


