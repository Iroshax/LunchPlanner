package com.iro.lunchplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String teamName;
}