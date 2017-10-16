
package com.oskar.kow.ActionsTrackerServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "broadcasts")
public class Broadcast {
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    String broadcastDate;
    
    @Getter
    @Setter
    Integer broadcastHour;
    
    @Getter
    @Setter
    Integer broadcastMinutes;
    
    //relation between channel and broadcast
    @ManyToOne
    @Getter//(onMethod = @__( @JsonIgnore ))
    @Setter
    Channel channel;
    
    //relation between program and broadcast
    
    @ManyToOne
    @Getter//(onMethod = @__( @JsonIgnore ))
    @Setter
    Program program;
    
    public Program testTheProgram()
    {
        return program;
    }
    public Channel testTheChannel()
    {
        return channel;
    }
    
    
    
}
