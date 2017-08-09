/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskar.kow.ActionsTrackerServer.model;

import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;

/**
 *
 * @author Oskar Kowalewski
 */
@Entity
@Table(name = "broadcasts")
public class Broadcast {
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
}
