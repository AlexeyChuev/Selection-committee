package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.repository.entity.SubmissionSubject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionSubjectRepository implements Repository<SubmissionSubject> {
    private Connection connection = ConnectionPoolFactory.getConnection();

    @Override
    public void create(SubmissionSubject entity) throws SQLException {
        String sql = "INSERT INTO SubmissionSubject VALUES(?,?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getSubmissionId());
            preparedStatement.setInt(3, entity.getSubjectId());
            preparedStatement.setInt(4, entity.getGrade());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SubmissionSubject oldEntity, SubmissionSubject newEntity) throws SQLException {
        String sql = "UPDATE SubmissionSubject SET submissionId=?, subjectId=?, grade=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, newEntity.getSubmissionId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.setInt(3, newEntity.getGrade());
            preparedStatement.setInt(4, oldEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SubmissionSubject entity) throws SQLException {
        String sql = "DELETE FROM SubmissionSubject WHERE id=" + entity.getId() + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public SubmissionSubject get(int entityId) throws SQLException {
        String sql = "SELECT * FROM SubmissionSubject WHERE id="+entityId+ ";";
        SubmissionSubject newSubmissionSubject = new SubmissionSubject();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newSubmissionSubject.setId(resultSet.getInt("id"));
            newSubmissionSubject.setSubmissionId(resultSet.getInt("submissionId"));
            newSubmissionSubject.setSubjectId(resultSet.getInt("subjectId"));
            newSubmissionSubject.setGrade(resultSet.getInt("grade"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newSubmissionSubject;
    }

    @Override
    public Collection<SubmissionSubject> findAll() throws SQLException {
        String sql = "SELECT * FROM SubmissionSubject;";
        Collection<SubmissionSubject> submissionSubjects = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                SubmissionSubject newSubmissionSubject = new SubmissionSubject();
                newSubmissionSubject.setId(resultSet.getInt("id"));
                newSubmissionSubject.setSubmissionId(resultSet.getInt("submissionId"));
                newSubmissionSubject.setSubjectId(resultSet.getInt("subjectId"));
                newSubmissionSubject.setGrade(resultSet.getInt("grade"));
                submissionSubjects.add(newSubmissionSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissionSubjects;
    }
}
