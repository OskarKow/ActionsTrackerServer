
package com.oskar.kow.ActionsTrackerServer.services;

import com.oskar.kow.ActionsTrackerServer.model.Channel;
import com.oskar.kow.ActionsTrackerServer.model.Program;
import java.util.List;
import java.util.function.Function;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oskar Kowalewski
 * 
 * Component with logic for Channels
 */
@Service
public class ChannelService extends EntityService<Channel>{
    
    /**
     * 
     * parameters injected by Spring framework
     */
    public ChannelService(EntityManager em) {
        super(em, Channel.class, Channel::getId);
    }
    
    /**
     * get list of all channels from database
     */
    public List<Channel> getAll()
    {
        return em.createQuery("SELECT c FROM Channel c", Channel.class).getResultList();
    }
    
    /**
     * 
     * @param name Channel name
     * @return list of channels with given name
     */
    public List<Channel> findByName(String name)
    {
        return em.createQuery("SELECT c FROM Channel c WHERE c.name LIKE :chName")
                .setParameter("chName", name)
                .getResultList();
    }
    
    
    
    
    @Transactional
    public void addChannel(Channel channel)
    {
        //no need to check for any conditions, we just add new channel to database
        save(channel);
    }
    
}
