package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Submission;
import net.chuiev.selcommittee.exception.EntityNotExistsException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Submission Repository. Part of implemeting pattern Repository.
 * Give CRUD-operations with Submission data.
 *
 * @author Oleksii Chuiev
 *
 */
public class SubmissionRepository implements Repository<Submission> {
    private static final Logger LOG = Logger.getLogger(SubmissionRepository.class);

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.SUBMISSION (FACULTY_ID, ENROLLEE_ID) VALUES(?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.SUBMISSION SET FACULTY_ID=?, ENROLLEE_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.SUBMISSION WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.SUBMISSION WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.SUBMISSION";

    private final static String FIND_BY_FACULTY_AND_ENROLLEE = "SELECT * FROM ADMIN.SUBMISSION WHERE FACULTY_ID=? AND ENROLLEE_ID=?";

    @Override
    public void create(Submission entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getEnrolleeId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public void update(Submission newEntity) {
        if (get(newEntity.getId()) == null) {
            throw new EntityNotExistsException();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getEnrolleeId());
            preparedStatement.setInt(3, newEntity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
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
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public Submission get(int entityId) {
        Submission newSubmission = new Submission();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            if (!resultSet.next()) {
                return null;
            }
            newSubmission.setId(resultSet.getInt("id"));
            newSubmission.setFacultyId(resultSet.getInt("FACULTY_ID"));
            newSubmission.setEnrolleeId(resultSet.getInt("ENROLLEE_ID"));
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newSubmission;
    }

    public Submission getByFacultyAndEnrolle(int facultyId, int enrolleeId) {
        Submission newSubmission = new Submission();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_FACULTY_AND_ENROLLEE);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.setInt(2, enrolleeId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            newSubmission.setId(resultSet.getInt("id"));
            newSubmission.setFacultyId(resultSet.getInt("FACULTY_ID"));
            newSubmission.setEnrolleeId(resultSet.getInt("ENROLLEE_ID"));
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newSubmission;
    }

    @Override
    public Collection<Submission> findAll() {
        Collection<Submission> submissions = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Submission newSubmission = new Submission();
                newSubmission.setId(resultSet.getInt("id"));
                newSubmission.setFacultyId(resultSet.getInt("FACULTY_ID"));
                newSubmission.setEnrolleeId(resultSet.getInt("ENROLLEE_ID"));
                submissions.add(newSubmission);
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return submissions;
    }

}
