package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.entity.FacultySubject;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class FacultySubjectRepository implements Repository<FacultySubject> {
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.FACULTY_SUBJECT (FACULTY_ID, SUBJECT_ID) VALUES(?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.FACULTY_SUBJECT SET FACULTY_ID=?, SUBJECT_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.FACULTY_SUBJECT WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.FACULTY_SUBJECT WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.FACULTY_SUBJECT";

    private final static String FIND_ALL_SUBJECTS_FOR_FACULTY = "SELECT * FROM ADMIN.FACULTY_SUBJECT WHERE FACULTY_ID=";

    @Override
    public void create(FacultySubject entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getSubjectId());
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
    public void update(FacultySubject newEntity) {
        if (get(newEntity.getId()) == null) {
            throw new EntityNotExistsException();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.setInt(3, newEntity.getId());
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
    public FacultySubject get(int entityId) {
        FacultySubject newFacultySubject = new FacultySubject();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();
            newFacultySubject.setId(resultSet.getInt("id"));
            newFacultySubject.setFacultyId(resultSet.getInt("FACULTY_ID"));
            newFacultySubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newFacultySubject;
    }

    @Override
    public Collection<FacultySubject> findAll() {
        Collection<FacultySubject> facultySubjects = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                FacultySubject newFacultySubject = new FacultySubject();
                newFacultySubject.setId(resultSet.getInt("id"));
                newFacultySubject.setFacultyId(resultSet.getInt("FACULTY_ID"));
                newFacultySubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                facultySubjects.add(newFacultySubject);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return facultySubjects;
    }


    public Collection<FacultySubject> findSubjectsForFaculty(Faculty faculty) {
        Collection<FacultySubject> facultySubjects = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_SUBJECTS_FOR_FACULTY + faculty.getId());
            while (resultSet.next()) {
                FacultySubject newFacultySubject = new FacultySubject();
                newFacultySubject.setId(resultSet.getInt("id"));
                newFacultySubject.setFacultyId(resultSet.getInt("FACULTY_ID"));
                newFacultySubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                facultySubjects.add(newFacultySubject);
            }
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return facultySubjects;
    }

}
