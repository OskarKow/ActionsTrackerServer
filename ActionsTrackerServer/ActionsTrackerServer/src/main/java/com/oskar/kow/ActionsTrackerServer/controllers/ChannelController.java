
package com.oskar.kow.ActionsTrackerServer.controllers;

import com.oskar.kow.ActionsTrackerServer.model.Channel;
import com.oskar.kow.ActionsTrackerServer.services.ChannelService;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oskar Kowalewski
 * 
 * Controller for handling actions on channels
 */
@RestController
public class ChannelController {
    
    final ChannelService channelService;
    
    /**
     * 
     * @param channelService injected by Spring framework 
     */
    public ChannelController(ChannelService channelService)
    {
        this.channelService = channelService;
    }
    
    @GetMapping("/channels")
    public List<Channel> getChannels()
    {
        return channelService.getAll();
    }
    
    @GetMapping("/channels/{id}")
    public ResponseEntity<Channel> getChannel(@PathVariable UUID id)
    {
        Channel channel = channelService.findById(id);
        if(isNull(channel))
        {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(channel);
        }
    }
    
    @GetMapping("/channels/findByName/{name}")
    public List<Channel> getByName(@PathVariable String name)
    {
        return channelService.findByName(name);
    }
    
    
    
    
}
