package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Enrollee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class EnrolleeRepository implements Repository<Enrollee> {
    private Connection connection = ConnectionPoolFactory.getConnection();

    @Override
    public void create(Enrollee entity) {
        String sql = "INSERT INTO Enrollee VALUES(?,?,?,?,?,?,?,?,?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getFirstname());
            preparedStatement.setString(3, entity.getLastname());
            preparedStatement.setString(4, entity.getSurname());
            preparedStatement.setString(5, entity.getCity());
            preparedStatement.setString(6, entity.getRegion());
            preparedStatement.setString(7, entity.getSchoolName());
            preparedStatement.setBoolean(8, entity.isBlocked());
            preparedStatement.setBlob(9, entity.getCertificate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Enrollee oldEntity, Enrollee newEntity) {
        String sql = "UPDATE Enrollee SET first_name=?, "+
                "last_name=?, surname=?, city=?, region=?, school_name=?,"+
                " email=?, certificate=?, isBlocked=? WHERE id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, newEntity.getFirstname());
            preparedStatement.setString(2, newEntity.getLastname());
            preparedStatement.setString(3, newEntity.getSurname());
            preparedStatement.setString(4, newEntity.getCity());
            preparedStatement.setString(5, newEntity.getRegion());
            preparedStatement.setString(6, newEntity.getSchoolName());
            preparedStatement.setBoolean(7, newEntity.isBlocked());
            preparedStatement.setBlob(8, newEntity.getCertificate());
            preparedStatement.setInt(9, oldEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Enrollee entity) {
        String sql = "DELETE FROM Enrolle WHERE id="+entity.getId() + ";";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch (SQLException e){e.printStackTrace();}
    }


    @Override
    public Enrollee get(int entityId) throws SQLException {
        String sql = "SELECT * FROM Enrolle WHERE id="+entityId+ ";";
        Enrollee newEnrollee = new Enrollee();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            newEnrollee.setId(resultSet.getInt("id"));
            newEnrollee.setFirstname(resultSet.getString("first_name"));
            newEnrollee.setLastname(resultSet.getString("last_name"));
            newEnrollee.setSurname(resultSet.getString("surname"));
            newEnrollee.setCity(resultSet.getString("city"));
            newEnrollee.setRegion(resultSet.getString("region"));
            newEnrollee.setBlocked(resultSet.getBoolean("isBlocked"));
            newEnrollee.setSchoolName(resultSet.getString("school_name"));
            newEnrollee.setCertificate(resultSet.getBlob("certificate"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newEnrollee;
    }


    @Override
    public Collection<Enrollee> findAll() {
        String sql = "SELECT * FROM Enrolle;";
        Collection<Enrollee> enrollees = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Enrollee newEnrollee = new Enrollee();
                newEnrollee.setId(resultSet.getInt("id"));
                newEnrollee.setFirstname(resultSet.getString("first_name"));
                newEnrollee.setLastname(resultSet.getString("last_name"));
                newEnrollee.setSurname(resultSet.getString("surname"));
                newEnrollee.setCity(resultSet.getString("city"));
                newEnrollee.setRegion(resultSet.getString("region"));
                newEnrollee.setBlocked(resultSet.getBoolean("isBlocked"));
                newEnrollee.setSchoolName(resultSet.getString("school_name"));
                newEnrollee.setCertificate(resultSet.getBlob("certificate"));
                enrollees.add(newEnrollee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollees;
    }
}
