package com.iro.lunchplanner.service;

import com.iro.lunchplanner.exception.LunchPlanException;
import com.iro.lunchplanner.model.User;
import com.iro.lunchplanner.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-27
 */
@Service
public class SendMailServiceImpl implements SendMailService{

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    public SendMailServiceImpl(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public String sendMail(String sessionName,String adminEmail) throws LunchPlanException {

        SimpleMailMessage message;

        try {
            List<User> userList = userRepository.findAll();
            if (userList.isEmpty()) {
                throw new LunchPlanException("Couldn't find any users to send email notifications.");
            } else {
                for (User user : userList) {
                    message = new SimpleMailMessage();
                    message.setSubject("Invitation to submit restaurant");
                    message.setFrom(adminEmail);
                    message.setTo(user.getEmail());

                    message.setText("Lunch session for " + sessionName + " is open now. Please submit your restaurant");
                    mailSender.send(message);
                }
                return "Success";
            }
        }catch(Exception e){
            throw new LunchPlanException("Error occurred while sending the email notifications : "+e.getMessage());
        }
    }
}
