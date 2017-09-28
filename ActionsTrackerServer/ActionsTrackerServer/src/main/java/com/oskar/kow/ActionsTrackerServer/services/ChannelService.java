
package com.oskar.kow.ActionsTrackerServer.services;

import com.oskar.kow.ActionsTrackerServer.model.Channel;
import java.util.List;
import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 *
 * @author Oskar Kowalewski
 * 
 * Component with logic for Channels
 */
public class ChannelService extends EntityService<Channel>{
    
    /**
     * 
     * parameters injected by Spring framework
     */
    public ChannelService(EntityManager em, Class<Channel> entityClass, Function<Channel, Object> idSupplier) {
        super(em, entityClass, idSupplier);
    }
    
    /**
     * get list of all channels from database
     */
    public List<Channel> getAll()
    {
        return em.createQuery("SELECT c FROM Channel c", Channel.class).getResultList();
    }
    
    public List<Channel> findByName(String name)
    {
        return em.createQuery("SELECT c FROM Channel c WHERE c.name LIKE chName")
                .setParameter("chName", name)
                .getResultList();
    }
    
}
