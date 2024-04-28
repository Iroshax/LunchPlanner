package com.iro.lunchplanner.repository;

import com.iro.lunchplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
