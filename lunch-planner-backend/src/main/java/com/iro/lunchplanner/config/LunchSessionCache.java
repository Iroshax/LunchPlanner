package com.iro.lunchplanner.config;

import com.iro.lunchplanner.dto.RestaurantDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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
        this.sessionCache = new ConcurrentHashMap<>();
    }

    /**
     * Get a restaurant entry based on the key
     * @param key key
     * @return restaurant
     */
    public RestaurantDto get(String key) {
        return sessionCache.get(key);
    }

    /**
     * Put new entry to the cache
     * @param key key
     * @param restaurantDto restaurant
     */
    public void put(String key, RestaurantDto restaurantDto) {
        sessionCache.put(key, restaurantDto);
    }

    /**
     * Check the cache is empty or not
     * @return true/false
     */
    public boolean isEmpty(){
        return sessionCache.isEmpty();
    }

    /**
     * Clear cache content
     */
    public void clearLunchSessionCache() {
        sessionCache.clear();
    }

    /**
     * Check specific record using the key
     * @param key key
     * @return true/false
     */
    public boolean containsKey(String key){
        return sessionCache.containsKey(key);
    }

    /**
     * Return the size of the cache
     * @return Integer
     */
    public Integer size(){
        return sessionCache.size();
    }

    /**
     * Select random entry from the cache
     * @return map entry
     */
    public Map.Entry<String, RestaurantDto> getRandomEntry() {
        int randomNumber = new Random().nextInt(sessionCache.size());
        Iterator<Map.Entry<String, RestaurantDto>> iterator = sessionCache.entrySet().iterator();
        for (int i = 0; i < randomNumber; i++) {
            iterator.next();
        }
        return iterator.next();
    }

}
