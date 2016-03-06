package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Submission;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionRepository implements Repository<Submission> {
    private Connection connection = ConnectionPoolFactory.getConnection();

    @Override
    public void create(Submission entity) throws SQLException {
        String sql = "INSERT INTO Submission VALUES(?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getFacultyId());
            preparedStatement.setInt(3, entity.getEnrolleeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Submission oldEntity, Submission newEntity) throws SQLException {
        String sql = "UPDATE Submission SET facultyId=?, enrolleeId=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getEnrolleeId());
            preparedStatement.setInt(3, oldEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Submission entity) throws SQLException {
        String sql = "DELETE FROM Submission WHERE id=" + entity.getId() + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public Submission get(int entityId) throws SQLException {
        String sql = "SELECT * FROM Submission WHERE id="+entityId+ ";";
        Submission newSubmission = new Submission();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newSubmission.setId(resultSet.getInt("id"));
            newSubmission.setFacultyId(resultSet.getInt("facultyId"));
            newSubmission.setEnrolleeId(resultSet.getInt("enrolleeId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newSubmission;
    }

    @Override
    public Collection<Submission> findAll() throws SQLException {
        String sql = "SELECT * FROM Submission;";
        Collection<Submission> submissions = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Submission newSubmission = new Submission();
                newSubmission.setId(resultSet.getInt("id"));
                newSubmission.setFacultyId(resultSet.getInt("facultyId"));
                newSubmission.setEnrolleeId(resultSet.getInt("enrolleeId"));
                submissions.add(newSubmission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }
}
