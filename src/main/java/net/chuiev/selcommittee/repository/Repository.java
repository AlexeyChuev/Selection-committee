package net.chuiev.selcommittee.repository;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public interface Repository<T> {
    void create(T entity) throws SQLException;
    void update(T newEntity) throws SQLException;
    void delete(int entityId) throws SQLException;
    T get(int entityId) throws SQLException;

    Collection<T> findAll() throws SQLException;
}
