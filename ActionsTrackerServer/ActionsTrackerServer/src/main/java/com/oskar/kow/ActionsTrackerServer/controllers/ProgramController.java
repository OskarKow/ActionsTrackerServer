
package com.oskar.kow.ActionsTrackerServer.controllers;

import com.oskar.kow.ActionsTrackerServer.model.Program;
import com.oskar.kow.ActionsTrackerServer.services.ProgramService;
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
 * Http requests mapping for Programs
 */
@RestController
public class ProgramController {
    final ProgramService programService;
    
    public ProgramController(ProgramService ps)
    {
        programService = ps;
    }
    
    @GetMapping("/programs")
    public List<Program> getPrograms()
    {
        return programService.getAll();
    }
    
    @GetMapping("/programs/{id}")
    public ResponseEntity<Program> getProgram(@PathVariable UUID id)
    {
        Program program = programService.findById(id);
        if(isNull(program))
        {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(program);
        }
    }
    
    @GetMapping("/programs/findByName/{name}")
    public List<Program> findByName(@PathVariable String name)
    {
        return programService.getWithName(name);
    }
    
    @PostMapping("/programs")
    public ResponseEntity<Program> addProgram(@RequestBody Program program, UriComponentsBuilder uriBuilder)
    {
        programService.save(program);
        URI location = uriBuilder.path("/programs/{id}").buildAndExpand(program.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
