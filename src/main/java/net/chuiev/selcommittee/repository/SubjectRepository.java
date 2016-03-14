package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Subject;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubjectRepository implements Repository<Subject> {
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.SUBJECT (NAME) VALUES(?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.SUBJECT SET name=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.SUBJECT WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.SUBJECT WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.SUBJECT";

    @Override
    public void create(Subject entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public void update(Subject newEntity) {
        if (get(newEntity.getId()) == null) {
            throw new EntityNotExistsException();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public void delete(int entityId) {
        if (get(entityId) == null) {
            throw new EntityNotExistsException();
        }
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_COMMAND + entityId);
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public Subject get(int entityId) {
        Subject newSubject = new Subject();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();
            newSubject.setId(resultSet.getInt("id"));
            newSubject.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newSubject;
    }

    @Override
    public Collection<Subject> findAll() {
        Collection<Subject> subjects = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Subject newSubject = new Subject();
                newSubject.setId(resultSet.getInt("id"));
                newSubject.setName(resultSet.getString("name"));
                subjects.add(newSubject);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return subjects;
    }
}
