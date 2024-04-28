package com.iro.lunchplanner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@Data
@Entity
@Table(name = "t_session_detail")
@NoArgsConstructor
@AllArgsConstructor
public class SessionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "session_Id")
    private Session session;
    private long userId;
    private String restaurantId;
}
