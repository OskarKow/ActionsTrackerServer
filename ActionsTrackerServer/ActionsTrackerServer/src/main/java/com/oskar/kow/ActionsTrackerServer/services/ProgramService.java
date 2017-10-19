
package com.oskar.kow.ActionsTrackerServer.services;

import com.oskar.kow.ActionsTrackerServer.model.Broadcast;
import com.oskar.kow.ActionsTrackerServer.model.Program;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskar Kowalewski
 * Component with logic for Programs
 */
@Service
public class ProgramService extends EntityService<Program>{
    
    public ProgramService(EntityManager em) {
        super(em, Program.class, Program::getId);
    }
    
    public List<Program> getAll()
    {
        return em.createQuery("SELECT p FROM Program p", Program.class).getResultList();
    }
    
    public List<Program> getWithName(String name)
    {
        return em.createQuery("SELECT p FROM Program p WHERE p.name LIKE :pName")
                .setParameter("pName", name)
                .getResultList();
    }
    
}
