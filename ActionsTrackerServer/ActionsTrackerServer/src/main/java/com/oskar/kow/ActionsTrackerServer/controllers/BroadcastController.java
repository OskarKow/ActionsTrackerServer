
package com.oskar.kow.ActionsTrackerServer.controllers;

import com.oskar.kow.ActionsTrackerServer.model.Broadcast;
import com.oskar.kow.ActionsTrackerServer.services.BroadcastService;
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
 * Rest controller for Broadcast entity
 */
@RestController
public class BroadcastController {
    
    final BroadcastService broadcastService;
    
    public BroadcastController(BroadcastService bs)
    {
        broadcastService = bs;
    }
    
    @GetMapping("/broadcasts")
    public List<Broadcast> getAll()
    {
        return broadcastService.getAll();
    }
    
    @GetMapping("/broadcasts/{id}")
    public ResponseEntity<Broadcast> getBroadcast(@PathVariable UUID id)
    {
        Broadcast broadcast = broadcastService.findById(id);
        if(isNull(broadcast))
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(broadcast);
    }
    
    @PostMapping("/broadcasts")
    public ResponseEntity<Broadcast> addBroadcast(@RequestBody Broadcast broadcast, UriComponentsBuilder uriBuilder)
    {
        broadcastService.addBroadcast(broadcast);
        URI location = uriBuilder.path("/broadcasts/{id}").buildAndExpand(broadcast.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
}
