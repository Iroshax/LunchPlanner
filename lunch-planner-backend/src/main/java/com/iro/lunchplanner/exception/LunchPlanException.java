package com.iro.lunchplanner.exception;

import lombok.NoArgsConstructor;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@NoArgsConstructor
public class LunchPlanException extends RuntimeException{

    public LunchPlanException(String message){
        super(message);
    }

}
