package com.iro.lunchplanner.IntegrationTests;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.dto.SessionDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.service.LunchPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchPlanService lunchPlanService;

    @Test
    void testInitiateSession() throws Exception {
        when(lunchPlanService.initiateSession(any(UserDto.class))).thenReturn(new LunchSessionCache());

        mockMvc.perform(post("/api/lunchPlanner/session/initiate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"testUser\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testEndSession() throws Exception {
        when(lunchPlanService.endSession(any(UserDto.class))).thenReturn(new RestaurantDto());

        mockMvc.perform(post("/api/lunchPlanner/session/end")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"testUser\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSessionList() throws Exception {
        when(lunchPlanService.getSessionList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/lunchPlanner/session"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetLatestSession() throws Exception {
        when(lunchPlanService.getLatestSession()).thenReturn(new SessionDto());

        mockMvc.perform(get("/api/lunchPlanner/session/latest"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSessionStatus() throws Exception {
        when(lunchPlanService.getSessionStatus()).thenReturn("Open");

        mockMvc.perform(get("/api/lunchPlanner/session/status"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetActiveSession() throws Exception {
        when(lunchPlanService.getActiveSession()).thenReturn(new LunchSessionCache());

        mockMvc.perform(get("/api/lunchPlanner/session/active"))
                .andExpect(status().isOk());
    }
}
