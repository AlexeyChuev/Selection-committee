package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class EnrolleeRepository implements Repository<Enrollee> {
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.ENROLLEE (FULL_NAME,city,region,school_name,certificate,USER_ID) VALUES(?,?,?,?,NULL,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.ENROLLEE SET FULL_NAME=?,CITY=?,REGION=?,SCHOOL_NAME=?,USER_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.ENROLLEE WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.ENROLLEE WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ENROLLEE";

    private final static String UPDATE_ISBLOCKED = "UPDATE ADMIN.ENROLLEE SET ISBLOCKED=? WHERE id=?";
    private final static String FIND_BY_USER_ID = "SELECT * FROM ADMIN.ENROLLEE WHERE USER_ID=";


    @Override
    public void create(Enrollee entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setString(1, entity.getFullName());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getRegion());
            preparedStatement.setString(4, entity.getSchoolName());
            preparedStatement.setInt(5, entity.getUserId());
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
    public void update(Enrollee newEntity) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setString(1, newEntity.getFullName());
            preparedStatement.setString(2, newEntity.getCity());
            preparedStatement.setString(3, newEntity.getRegion());
            preparedStatement.setString(4, newEntity.getSchoolName());
            preparedStatement.setInt(5, newEntity.getUserId());
            preparedStatement.setInt(6, newEntity.getId());
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
    public void delete(int entityId) {
        if (get(entityId) == null) throw new EntityNotExistsException();
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
    public Enrollee get(int entityId) {
        Enrollee newEnrollee = new Enrollee();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();
            newEnrollee.setId(resultSet.getInt("id"));
            newEnrollee.setFullName(resultSet.getString("full_name"));
            newEnrollee.setCity(resultSet.getString("city"));
            newEnrollee.setRegion(resultSet.getString("region"));
            newEnrollee.setUserId(resultSet.getInt("user_id"));
            newEnrollee.setSchoolName(resultSet.getString("school_name"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newEnrollee;
    }

    @Override
    public Collection<Enrollee> findAll() {
        Collection<Enrollee> enrollees = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Enrollee newEnrollee = new Enrollee();
                newEnrollee.setId(resultSet.getInt("id"));
                newEnrollee.setFullName(resultSet.getString("full_name"));
                newEnrollee.setCity(resultSet.getString("city"));
                newEnrollee.setRegion(resultSet.getString("region"));
                newEnrollee.setUserId(resultSet.getInt("user_id"));
                newEnrollee.setSchoolName(resultSet.getString("school_name"));
                enrollees.add(newEnrollee);            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return enrollees;
    }

    public void updateStatusIsBlocked(Enrollee newEntity, boolean isBlocked) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ISBLOCKED);
            preparedStatement.setBoolean(1, isBlocked);
            preparedStatement.setInt(2, newEntity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }

    }

    public Enrollee findByUserId(int userId) {
        Enrollee newEnrollee = new Enrollee();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_BY_USER_ID + userId);
            resultSet.next();
            newEnrollee.setId(resultSet.getInt("id"));
            newEnrollee.setFullName(resultSet.getString("full_name"));
            newEnrollee.setCity(resultSet.getString("city"));
            newEnrollee.setRegion(resultSet.getString("region"));
            newEnrollee.setUserId(resultSet.getInt("user_id"));
            newEnrollee.setSchoolName(resultSet.getString("school_name"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newEnrollee;
    }


}
