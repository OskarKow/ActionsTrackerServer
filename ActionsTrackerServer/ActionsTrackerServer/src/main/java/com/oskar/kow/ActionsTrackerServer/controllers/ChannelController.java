
package com.oskar.kow.ActionsTrackerServer.controllers;

import com.oskar.kow.ActionsTrackerServer.model.Channel;
import com.oskar.kow.ActionsTrackerServer.services.ChannelService;
import java.net.URI;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    
    @GetMapping("/channels/findByName/{chName}")
    public List<Channel> getByName(@PathVariable String chName)
    {
        return channelService.findByName(chName);
    }
    
    @PostMapping("/channels")
    public ResponseEntity<Channel> addChannel(@RequestBody Channel channel, UriComponentsBuilder uriBuilder)
    {
        channelService.addChannel(channel);
        URI location = uriBuilder.path("/channels/{id}").buildAndExpand(channel.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    //@PutMapping("/channels/appendToBroadcasts/{id}")
    
   
}
