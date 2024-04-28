package com.iro.lunchplanner.config;

import com.iro.lunchplanner.dto.RestaurantDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Component
@Data
public class LunchSessionCache {

    private Map<String, RestaurantDto> sessionCache;

    public LunchSessionCache() {
        this.sessionCache = new LinkedHashMap<>();
    }

    public RestaurantDto get(String key) {
        return sessionCache.get(key);
    }

    public void put(String key, RestaurantDto restaurantDto) {
        sessionCache.put(key, restaurantDto);
    }

    public boolean isEmpty(){
        return sessionCache.isEmpty();
    }
    public void clearLunchSessionCache() {
        sessionCache.clear();
    }

    public boolean containsKey(String key){
        return sessionCache.containsKey(key);
    }

    public Integer size(){
        return sessionCache.size();
    }

    public Map.Entry<String, RestaurantDto> getRandomEntry() {
        int randomNumber = new Random().nextInt(sessionCache.size());
        Iterator<Map.Entry<String, RestaurantDto>> iterator = sessionCache.entrySet().iterator();
        for (int i = 0; i < randomNumber; i++) {
            iterator.next();
        }
        return iterator.next();
    }



}
