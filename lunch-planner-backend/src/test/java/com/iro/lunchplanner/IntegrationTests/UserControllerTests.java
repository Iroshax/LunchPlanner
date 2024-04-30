package com.iro.lunchplanner.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/api/lunchPlanner/user/login")
                        .contentType("application/json")
                        .content("{\"userName\":\"irosha1\",\"password\":\"123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/api/lunchPlanner/user/register")
                        .contentType("application/json")
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"userName\":\"johndoe\",\"password\":\"password\",\"teamName\":\"Team1\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserList() throws Exception {
        mockMvc.perform(get("/api/lunchPlanner/user")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
