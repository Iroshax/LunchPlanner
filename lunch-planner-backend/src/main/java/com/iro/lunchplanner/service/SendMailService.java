package com.iro.lunchplanner.service;
/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
public interface SendMailService {
     /**
      * Send email notification to all the users.
      * @param sessionName Session Name
      * @return String
      */
     String sendMail(String sessionName, String adminEmail);
}
