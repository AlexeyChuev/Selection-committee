package net.chuiev.selcommittee.repository;


import java.util.Collection;

/**
 * Interface Repository. Main part of implemeting
 * pattern Repository. Define CRUD-operations
 * for implemented classes.
 *
 * @author Oleksii Chuiev
 *
 * @param <T>
 *         type of the object
 */
public interface Repository<T> {
    /**
     * Add object to repository.
     *
     * @param entity - object to be add
     */
    void create(T entity);

    /**
     * Edit object in repository.
     *
     * @param newEntity - object to be edit
     */
    void update(T newEntity);

    /**
     * Delete object in repository by id.
     *
     * @param entityId - objects id to be deleted
     */
    void delete(int entityId);

    /**
     * Return object from repository by id.
     *
     * @param entityId - objects id to be returned
     * @return T-entity
     */
    T get(int entityId);

    /**
     * Return all objects from repository.
     *
     * @return List(Collection) of T-entities
     */
    Collection<T> findAll();
}
