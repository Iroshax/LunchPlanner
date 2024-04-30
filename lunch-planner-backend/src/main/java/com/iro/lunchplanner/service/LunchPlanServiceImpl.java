package com.iro.lunchplanner.service;

import com.iro.lunchplanner.config.LunchSessionCache;
import com.iro.lunchplanner.constants.LunchPlanConstants;
import com.iro.lunchplanner.dto.SessionDto;
import com.iro.lunchplanner.dto.UserDto;
import com.iro.lunchplanner.dto.RestaurantDto;
import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.Session;
import com.iro.lunchplanner.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Service
public class LunchPlanServiceImpl implements LunchPlanService{

    private final SessionRepository sessionRepository;
    private final LunchSessionCache lunchSessionCache;
    private final SendMailService sendMailService;

    public LunchPlanServiceImpl(SessionRepository sessionRepository, LunchSessionCache lunchSessionCache, SendMailService sendMailService) {
        this.sessionRepository = sessionRepository;
        this.lunchSessionCache = lunchSessionCache;
        this.sendMailService = sendMailService;
    }

    @Override
    @Transactional
    public LunchSessionCache initiateSession(UserDto user) throws LunchPlanException {

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Session session = new Session();
        try{
            session.setSessionAdmin(user.getUserName());
            session.setSessionName(localDate+" , "+localDate.getDayOfWeek().name()+" Lunch");
            session.setSessionStatus(LunchPlanConstants.OPEN);
            session.setStartTime(localDateTime);
            List<Session> activeSessions = sessionRepository.checkOpenSessions(session.getSessionName(),session.getSessionStatus());
            if(activeSessions.isEmpty()){
               sessionRepository.save(session);
               sendMailService.sendMail(session.getSessionName(),user.getEmail());
            }else{
               throw new LunchPlanException("A session for the day "+localDate+" is already open");
            }
        }catch(Exception e){
            throw new LunchPlanException("Error occurred while initiating lunch session: "+e.getMessage());

        }
        return lunchSessionCache;
    }

    @Override
    public LunchSessionCache getActiveSession() {

        try{
            if(lunchSessionCache.isEmpty()){
                return new LunchSessionCache();
            }else{
                return lunchSessionCache;
            }
        }catch(Exception e ){
           throw new LunchPlanException("Error occurred while getting the active session: "+e.getMessage());
        }
    }

    @Override
    public String getSessionStatus() throws LunchPlanException{

        try{
            Optional<Session> openSession = sessionRepository.getOpenSessionByStatus("Open");
            if(openSession.isPresent()){
                return LunchPlanConstants.OPEN;
            }else {
                return LunchPlanConstants.CLOSED;
            }

        }catch(Exception e){
            throw new LunchPlanException("Error occurred while getting the session status"+e.getMessage());
        }
    }

    @Override
    public SessionDto getLatestSession() {

        List<SessionDto> sessionDtoList;
        sessionDtoList = getSessionList();
        return sessionDtoList.getFirst();
    }

    @Override
    public RestaurantDto endSession(UserDto user) throws LunchPlanException{

        Session session;
        LocalDateTime localDateTime = LocalDateTime.now();
        RestaurantDto selectedRestaurantDto;

        try{
            Optional<Session> activeSession = sessionRepository.getOpenSession(user.getUserName(),LunchPlanConstants.OPEN);

            if(activeSession.isPresent()){
                selectedRestaurantDto = selectRandomRestaurant();
                session = activeSession.get();
                session.setEndTime(localDateTime);
                session.setSessionStatus(LunchPlanConstants.CLOSED);
                session.setSelectedRestaurant(selectRandomRestaurant().getName());
                sessionRepository.save(session);
                lunchSessionCache.clearLunchSessionCache();
            }else{
                throw new LunchPlanException("No Open Session Found for the User: "+user.getUserName());
            }

        }catch(Exception e){
            throw new LunchPlanException("Error occurred while closing the lunch session: "+e.getMessage());
        }

        return selectedRestaurantDto;
    }

    @Override
    public List<SessionDto> getSessionList() throws LunchPlanException{

        List<Session> sessionList;
        List<SessionDto> sessionDtoList;
        try{
            sessionList = sessionRepository.findAll();
            sessionList.sort(Comparator.comparing(Session::getStartTime).reversed());
            sessionDtoList = sessionListToDto(sessionList);
        }catch(Exception e){
            throw new LunchPlanException("Error occurred while fetching the session list: "+e.getMessage());
        }
        return sessionDtoList;
    }

    /**
     * Select random restaurant from the cache. If no submissions, return default "No submissions' object
     * @return RestaurantDto
     */
    public RestaurantDto selectRandomRestaurant() {

        RestaurantDto restaurantDto;

        if(lunchSessionCache.getSessionCache() == null){
            restaurantDto = new RestaurantDto("No Submissions", "N/A", "N/A", "N/A");
            return restaurantDto;
        }else{
            if(lunchSessionCache.isEmpty()){
                restaurantDto = new RestaurantDto("No Submissions", "N/A", "N/A", "N/A");
                return restaurantDto;
            }else {
                return lunchSessionCache.getRandomEntry().getValue();
            }
        }
    }

    /**
     * Map session list to sessionDto list
     * @param sessionList sessionList
     * @return List<SessionDto>
     */
    private List<SessionDto> sessionListToDto(List<Session> sessionList) {

        SessionDto sessionDto = null;
        List<SessionDto> sessionDtoList = new ArrayList<>();
        for(Session session:sessionList){

            sessionDto = new SessionDto(session.getId(),
                    null,
                    session.getSessionName(),
                    session.getSessionAdmin(),
                    session.getSelectedRestaurant(),
                    session.getSessionStatus(),
                    session.getStartTime(),
                    session.getEndTime());
            sessionDto.setId(session.getId());
            sessionDtoList.add(sessionDto);

        }
        return sessionDtoList;
    }
}
