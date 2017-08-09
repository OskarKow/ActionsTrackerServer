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

/**
 *
 * @author Oskar Kowalewski
 */
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "programs")
public class Program {
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    @Column(nullable=false)
    String name;
    
}
