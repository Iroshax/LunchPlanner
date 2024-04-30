package com.iro.lunchplanner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Data
@Entity
@Table(name = "t_session")
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    List<SessionDetail> sessionDetailList;
    private String sessionName;
    private String sessionAdmin;
    private String selectedRestaurant;
    private String sessionStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
