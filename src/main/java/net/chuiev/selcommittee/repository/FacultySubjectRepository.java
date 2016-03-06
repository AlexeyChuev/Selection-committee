package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.FacultySubject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class FacultySubjectRepository implements Repository<FacultySubject> {
    private Connection connection = ConnectionCreator.getConnection();

    @Override
    public void create(FacultySubject entity) throws SQLException {
        String sql = "INSERT INTO FacultySubject VALUES(?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getFacultyId());
            preparedStatement.setInt(3, entity.getSubjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FacultySubject newEntity) throws SQLException {
        String sql = "UPDATE FacultySubject SET facultyId=?, subjectId=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, newEntity.getFacultyId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int entityId) throws SQLException {
        String sql = "DELETE FROM FacultySubject WHERE id=" + entityId + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public FacultySubject get(int entityId) throws SQLException {
        String sql = "SELECT * FROM FacultySubject WHERE id="+entityId+ ";";
        FacultySubject newFacultySubject = new FacultySubject();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newFacultySubject.setId(resultSet.getInt("id"));
            newFacultySubject.setFacultyId(resultSet.getInt("facultyId"));
            newFacultySubject.setSubjectId(resultSet.getInt("subjectId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newFacultySubject;
    }

    @Override
    public Collection<FacultySubject> findAll() throws SQLException {
        String sql = "SELECT * FROM FacultySubject;";
        Collection<FacultySubject> facultySubjects = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                FacultySubject newFacultySubject = new FacultySubject();
                newFacultySubject.setId(resultSet.getInt("id"));
                newFacultySubject.setFacultyId(resultSet.getInt("facultyId"));
                newFacultySubject.setSubjectId(resultSet.getInt("subjectId"));
                facultySubjects.add(newFacultySubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultySubjects;
    }
}
