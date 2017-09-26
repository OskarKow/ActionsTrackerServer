/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oskar.kow.ActionsTrackerServer.services;

import com.oskar.kow.ActionsTrackerServer.model.Channel;
import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 *
 * @author Oskar Kowalewski
 * 
 * Component with logic for Channels
 */
public class ChannelService extends EntityService<Channel>{
    
    public ChannelService(EntityManager em, Class<Channel> entityClass, Function<Channel, Object> idSupplier) {
        super(em, entityClass, idSupplier);
    }
    
}
