
package com.oskar.kow.ActionsTrackerServer.model;

import com.oskar.kow.ActionsTrackerServer.services.ChannelService;
import com.oskar.kow.ActionsTrackerServer.services.ProgramService;
import com.oskar.kow.ActionsTrackerServer.services.exceptions.ChannelNotFoundException;
import com.oskar.kow.ActionsTrackerServer.services.exceptions.ProgramNotFoundException;
import static java.util.Objects.isNull;
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
    @Setter
    Channel channel;
    
    //relation between program and broadcast
    
    @ManyToOne
    @Setter
    Program program;
    
    public void boundToProgram(ProgramService ps)
    {
        UUID programID = program.getId();
        Program broadcastProgram = ps.findById(programID);
        if(isNull(broadcastProgram))
        {
            //Wrong program ID in broadcast
            throw new ProgramNotFoundException();
        }
        //valid program ID
        broadcastProgram.getBroadcasts().add(this);
    }
    
    public void boundToChannel(ChannelService cs)
    {
        UUID channelID = channel.getId();
        Channel broadcastChannel = cs.findById(channelID);
        if(isNull(broadcastChannel))
        {
            //Wrong channel ID in broadcast
            throw new ChannelNotFoundException();
        }
        //valid channel
        broadcastChannel.getBroadcasts().add(this);
    }
    
    public UUID getProgramId()
    {
        return program.getId();
    }
    
    public UUID getChannelId()
    {
        return channel.getId();
    }
}
