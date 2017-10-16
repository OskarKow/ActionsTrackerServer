
package com.oskar.kow.ActionsTrackerServer.services;

import com.oskar.kow.ActionsTrackerServer.model.Broadcast;
import com.oskar.kow.ActionsTrackerServer.model.Channel;
import com.oskar.kow.ActionsTrackerServer.model.Program;
import com.oskar.kow.ActionsTrackerServer.services.exceptions.ChannelNotFoundException;
import com.oskar.kow.ActionsTrackerServer.services.exceptions.ProgramNotFoundException;
import com.oskar.kow.ActionsTrackerServer.services.exceptions.WrongTimeFormatException;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oskar Kowalewski
 * 
 * Logic service for Broadcast entity
 */
@Service
public class BroadcastService extends EntityService<Broadcast>{
    
    final ProgramService programService;
    final ChannelService channelService;
    
    public BroadcastService(EntityManager em, ProgramService ps, ChannelService cs)
    {
        super(em, Broadcast.class, Broadcast::getId);
        programService = ps;
        channelService = cs;
    }
    
    public List<Broadcast> getAll()
    {
        return em.createQuery("SELECT b FROM Broadcast b").getResultList();
    }
    
    /**
     * 
     * @param broadcast
     * 
     * Adding new broadcast to database. Broadcast contains id of channel and id of program.
     * If either channel or program were not found the rollback takes place.
     */
    @Transactional
    public void addBroadcast(Broadcast broadcast)
    {
        //check if program is valid
        UUID programId = broadcast.testTheProgram().getId();
        Program program = programService.findById(programId);
        if(isNull(program))
        {
            //Wrong program ID in broadcast
            throw new ProgramNotFoundException();
        }
        //check if channel is valid
        UUID channelId = broadcast.testTheChannel().getId();
        Channel channel = channelService.findById(channelId);
        if(isNull(channel))
        {
            //wrong channel ID in broadcast
            throw new ChannelNotFoundException();
        }
        //check for time format
        int bHour = broadcast.getBroadcastHour();
        int bMinutes = broadcast.getBroadcastMinutes();
        if(bHour < 0 || bHour > 24) throw new WrongTimeFormatException();
        if(bMinutes < 0 || bMinutes > 60) throw new WrongTimeFormatException();
        
        //add broadcast to channel and program (DB relationship)
        
        channel.getBroadcasts().add(broadcast);
        program.getBroadcasts().add(broadcast);
        save(broadcast);
    }
    
}
