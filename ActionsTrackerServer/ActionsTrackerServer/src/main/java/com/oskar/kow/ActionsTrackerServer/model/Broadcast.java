/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskar.kow.ActionsTrackerServer.model;

import java.util.UUID;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Oskar Kowalewski
 */
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "broadcasts")
public class Broadcast {
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Temporal(TIMESTAMP)
    Date broadcastDate;
    
    @Getter
    @Setter
    Integer broadcastHour;
    
    @Getter
    @Setter
    Integer broadcastMinutes;
    
    //relation between channel and broadcast
    @ManyToOne
    Channel channel;
    
    //relation between program and broadcast
    
    @ManyToOne(optional = true)
    Program program;
    
    
    
}
