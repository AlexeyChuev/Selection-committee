package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.SubmissionSubject;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionSubjectRepository implements Repository<SubmissionSubject> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.SUBMISSION_SUBJECT (SUBMISSION_ID, SUBJECT_ID, GRADE) VALUES(?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.SUBMISSION_SUBJECT SET SUBMISSION_ID=?, SUBJECT_ID=?, grade=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.SUBMISSION_SUBJECT WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT";

    @Override
    public void create(SubmissionSubject entity){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND))
        {
            preparedStatement.setInt(1, entity.getSubmissionId());
            preparedStatement.setInt(2, entity.getSubjectId());
            preparedStatement.setInt(3, entity.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
    }

    @Override
    public void update(SubmissionSubject newEntity){
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND))
        {
            preparedStatement.setInt(1, newEntity.getSubmissionId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.setInt(3, newEntity.getGrade());
            preparedStatement.setInt(4, newEntity.getId());
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
    public SubmissionSubject get(int entityId){
        SubmissionSubject newSubmissionSubject = new SubmissionSubject();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND+entityId);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newSubmissionSubject.setId(resultSet.getInt("id"));
            newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
            newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
            newSubmissionSubject.setGrade(resultSet.getInt("grade"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return newSubmissionSubject;
    }

    @Override
    public Collection<SubmissionSubject> findAll(){
        Collection<SubmissionSubject> submissionSubjects = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next())
            {
                SubmissionSubject newSubmissionSubject = new SubmissionSubject();
                newSubmissionSubject.setId(resultSet.getInt("id"));
                newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
                newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                newSubmissionSubject.setGrade(resultSet.getInt("grade"));
                submissionSubjects.add(newSubmissionSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return submissionSubjects;
    }
}
