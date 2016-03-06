package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Submission;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionRepository implements Repository<Submission> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.SUBMISSION (FACULTY_ID, ENROLLEE_ID) VALUES(?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.SUBMISSION SET FACULTY_ID=?, ENROLLEE_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.SUBMISSION WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM Submission WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.SUBMISSION";

    @Override
    public void create(Submission entity){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND))
        {
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getEnrolleeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
    }

    @Override
    public void update(Submission newEntity){
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND))
        {
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getEnrolleeId());
            preparedStatement.setInt(3, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
    }

    @Override
    public void delete(int entityId){
        if (get(entityId) == null) throw new EntityNotExistsException();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_COMMAND + entityId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
    }

    @Override
    public Submission get(int entityId){
        Submission newSubmission = new Submission();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND+entityId);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newSubmission.setId(resultSet.getInt("id"));
            newSubmission.setFacultyId(resultSet.getInt("FACULTY_ID"));
            newSubmission.setEnrolleeId(resultSet.getInt("ENROLLEE_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return newSubmission;
    }

    @Override
    public Collection<Submission> findAll(){
        Collection<Submission> submissions = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next())
            {
                Submission newSubmission = new Submission();
                newSubmission.setId(resultSet.getInt("id"));
                newSubmission.setFacultyId(resultSet.getInt("FACULTY_ID"));
                newSubmission.setEnrolleeId(resultSet.getInt("ENROLLEE_ID"));
                submissions.add(newSubmission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return submissions;
    }
}
