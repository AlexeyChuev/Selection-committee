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
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.ENROLLEE (FULL_NAME,city,region,school_name,certificate,USER_ID) VALUES(?,?,?,?,NULL,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.ENROLLEE SET FULL_NAME=?,CITY=?,REGION=?,SCHOOL_NAME=?,USER_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.ENROLLEE WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.ENROLLEE WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ENROLLEE";

    private final static String UPDATE_ISBLOCKED = "UPDATE ADMIN.ENROLLEE SET ISBLOCKED=? WHERE id=?";


    @Override
    public void create(Enrollee entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND)) {
            preparedStatement.setString(1, entity.getFullName());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getRegion());
            preparedStatement.setString(4, entity.getSchoolName());
            preparedStatement.setInt(5, entity.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(Enrollee newEntity) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND)) {
            preparedStatement.setString(1, newEntity.getFullName());
            preparedStatement.setString(2, newEntity.getCity());
            preparedStatement.setString(3, newEntity.getRegion());
            preparedStatement.setString(4, newEntity.getSchoolName());
            preparedStatement.setInt(5, newEntity.getUserId());
            preparedStatement.setInt(6, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void delete(int entityId) {
        if (get(entityId) == null) throw new EntityNotExistsException();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_COMMAND + entityId);
        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    @Override
    public Enrollee get(int entityId) {
        Enrollee newEnrollee = new Enrollee();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();

            newEnrollee.setId(resultSet.getInt("id"));
            newEnrollee.setFullName(resultSet.getString("full_name"));
            newEnrollee.setCity(resultSet.getString("city"));
            newEnrollee.setRegion(resultSet.getString("region"));

            newEnrollee.setUserId(resultSet.getInt("user_id"));
            newEnrollee.setSchoolName(resultSet.getString("school_name"));

        } catch (SQLException e) {
            e.printStackTrace();

        }
            return newEnrollee;
    }

    @Override
    public Collection<Enrollee> findAll() {
        Collection<Enrollee> enrollees = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Enrollee newEnrollee = new Enrollee();
                newEnrollee.setId(resultSet.getInt("id"));
                newEnrollee.setFullName(resultSet.getString("full_name"));
                newEnrollee.setCity(resultSet.getString("city"));
                newEnrollee.setRegion(resultSet.getString("region"));

                newEnrollee.setUserId(resultSet.getInt("user_id"));
                newEnrollee.setSchoolName(resultSet.getString("school_name"));

                enrollees.add(newEnrollee);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return enrollees;
    }

    public void updateStatusIsBlocked(Enrollee newEntity, boolean isBlocked) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ISBLOCKED)) {
            preparedStatement.setBoolean(1, isBlocked);
            preparedStatement.setInt(2, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


}
