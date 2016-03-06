package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class FacultyRepository implements Repository<Faculty> {
    private Connection connection = ConnectionCreator.getConnection();

    @Override
    public void create(Faculty entity) throws SQLException {
        String sql = "INSERT INTO Faculty VALUES(?,?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getBudgetVolume());
            preparedStatement.setInt(4, entity.getTotalVolume());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Faculty newEntity) throws SQLException {
        String sql = "UPDATE Faculty SET name=?, budgetVolume=?, totalVolume=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, newEntity.getBudgetVolume());
            preparedStatement.setInt(3, newEntity.getTotalVolume());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int entityId) throws SQLException {
        String sql = "DELETE FROM Faculty WHERE id=" + entityId + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }

    @Override
    public Faculty get(int entityId) throws SQLException {
        String sql = "SELECT * FROM Faculty WHERE id="+entityId+ ";";
        Faculty newFaculty = new Faculty();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newFaculty.setId(resultSet.getInt("id"));
            newFaculty.setName(resultSet.getString("name"));
            newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
            newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newFaculty;
    }

    @Override
    public Collection<Faculty> findAll() throws SQLException {
        String sql = "SELECT * FROM Faculty;";
        Collection<Faculty> faculties = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Faculty newFaculty = new Faculty();
                newFaculty.setId(resultSet.getInt("id"));
                newFaculty.setName(resultSet.getString("name"));
                newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
                newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
                faculties.add(newFaculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculties;
    }
}
