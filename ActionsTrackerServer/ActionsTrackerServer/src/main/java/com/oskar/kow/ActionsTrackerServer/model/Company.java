/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskar.kow.ActionsTrackerServer.model;
/**
 *
 * @author Oskar Kowalewski
 */
import javax.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "companies")
public class Company {
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    String name; //company name
    
    @Getter
    @Setter
    Float actionsValue; //value TO DO will it be one currency or create class which contains value AND currency ???
    
    @Getter
    @Setter
    Boolean isActionsRising; //true if actions are rising
    
    @Getter
    @Setter
    Float changeValue; //percentage value of chang
}
