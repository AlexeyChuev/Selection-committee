package net.chuiev.selcommittee.repository;


import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public interface Repository<T> {
    void create(T entity);

    void update(T newEntity);

    void delete(int entityId);

    T get(int entityId);

    Collection<T> findAll();
}
