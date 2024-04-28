package com.iro.lunchplanner.repository;

import com.iro.lunchplanner.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    @Query(value="SELECT S FROM Session S WHERE S.sessionName=?1 AND sessionStatus=?2")
    List<Session> checkOpenSessions(String sessionName, String sessionStatus);

    @Query(value="SELECT S FROM Session S WHERE S.sessionAdmin=?1 AND S.sessionStatus=?2")
    Optional<Session> getOpenSession(String userName,String status);

    @Query(value="SELECT S FROM Session S WHERE S.sessionStatus=?1")
    Optional<Session> getOpenSessionByStatus(String status);
}
