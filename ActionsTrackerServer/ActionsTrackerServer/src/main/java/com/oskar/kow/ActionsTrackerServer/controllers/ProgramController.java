
package com.oskar.kow.ActionsTrackerServer.controllers;

import com.oskar.kow.ActionsTrackerServer.model.Program;
import com.oskar.kow.ActionsTrackerServer.services.ProgramService;
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
}
