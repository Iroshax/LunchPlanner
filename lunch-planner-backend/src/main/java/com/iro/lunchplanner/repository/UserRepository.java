package com.iro.lunchplanner.repository;

import com.iro.lunchplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value="SELECT U FROM User U WHERE U.userName=?1 AND U.password=?2")
    Optional<User> findByUserNamePassword(String userName, String password);

    @Query(value="SELECT U FROM User U WHERE U.userName=?1")
    Optional<User> findByUserName(String userName);
}
