package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.FacultySubject;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class FacultySubjectRepository implements Repository<FacultySubject> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.FACULTY_SUBJECT (FACULTY_ID, SUBJECT_ID) VALUES(?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.FACULTY_SUBJECT SET FACULTY_ID=?, SUBJECT_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.FACULTY_SUBJECT WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.FACULTY_SUBJECT WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.FACULTY_SUBJECT";

    @Override
    public void create(FacultySubject entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND))
        {
            preparedStatement.setInt(1, entity.getFacultyId());
            preparedStatement.setInt(2, entity.getSubjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public void update(FacultySubject newEntity) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND))
        {
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.setInt(3, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
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
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public FacultySubject get(int entityId){
        FacultySubject newFacultySubject = new FacultySubject();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND+entityId);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newFacultySubject.setId(resultSet.getInt("id"));
            newFacultySubject.setFacultyId(resultSet.getInt("FACULTY_ID"));
            newFacultySubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return newFacultySubject;
    }

    @Override
    public Collection<FacultySubject> findAll(){
        Collection<FacultySubject> facultySubjects = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next())
            {
                FacultySubject newFacultySubject = new FacultySubject();
                newFacultySubject.setId(resultSet.getInt("id"));
                newFacultySubject.setFacultyId(resultSet.getInt("FACULTY_ID"));
                newFacultySubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                facultySubjects.add(newFacultySubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return facultySubjects;
    }
}
