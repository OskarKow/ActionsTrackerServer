
package com.oskar.kow.ActionsTrackerServer.services;

import java.util.UUID;
import java.util.function.Function;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oskar Kowalewski
 * 
 * 
 * Base class for all service classes. Contains basic methods needed in all services
 * 
 * @param <T> type of objects which will be managed by this EntityService
 */
abstract public class EntityService<T> {
    final EntityManager em;
    private final Class<T> entityClass;
    private final Function<T, Object> idSupplier;
    
    public EntityService(EntityManager em, Class<T> entityClass, Function<T, Object> idSupplier)
    {
        this.em = em;
        this.entityClass = entityClass;
        this.idSupplier = idSupplier;
    }
    
    /**
     * Method for saving new entities or updating existing ones.
     * 
     * @param entity 
     */
    @Transactional
    public void save(T entity)
    {
        if(em.find(entityClass, idSupplier.apply(entity)) == null)
        {
            //if this ID doesnt exist in database
            em.persist(entity);
        }else{
            //we take the object to managed state
            em.merge(entity);
        }
    }
    
    /**
     * finding entity with given id
     * 
     * @param id entity's is
     * @return found object or null if there is no object with this id
     */
    public T findById(UUID id)
    {
        return em.find(entityClass, id);
    }
}
