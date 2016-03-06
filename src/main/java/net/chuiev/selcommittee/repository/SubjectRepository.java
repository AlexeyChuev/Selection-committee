package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubjectRepository implements Repository<Subject> {
    private Connection connection = ConnectionCreator.getConnection();

    public static void main(String[] args) throws SQLException {
        SubjectRepository subjectRepository = new SubjectRepository();
        subjectRepository.create(new Subject(1, "Name1"));
        subjectRepository.create(new Subject(2, "Name2"));
        subjectRepository.create(new Subject(3, "Name3"));
    }

    @Override
    public void create(Subject entity){
        String sql = "INSERT INTO Subject VALUES(?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subject newEntity){
        String sql = "UPDATE Subject SET name=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int entityId){
        String sql = "DELETE FROM Subject WHERE id=" + entityId + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public Subject get(int entityId){
        String sql = "SELECT * FROM Subject WHERE id="+entityId+ ";";
        Subject newSubject = new Subject();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newSubject.setId(resultSet.getInt("id"));
            newSubject.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newSubject;
    }

    @Override
    public Collection<Subject> findAll() throws SQLException {
        String sql = "SELECT * FROM Subject;";
        Collection<Subject> subjects = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Subject newSubject = new Subject();
                newSubject.setId(resultSet.getInt("id"));
                newSubject.setName(resultSet.getString("name"));
                subjects.add(newSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
}
