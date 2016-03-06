package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class FacultyRepository implements Repository<Faculty> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.FACULTY (NAME, BUDGETVOLUME, TOTALVOLUME) VALUES(?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.FACULTY SET NAME=?, BUDGETVOLUME=?, TOTALVOLUME=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.FACULTY WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM Faculty WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.FACULTY";

    @Override
    public void create(Faculty entity){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getBudgetVolume());
            preparedStatement.setInt(3, entity.getTotalVolume());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
    }

    @Override
    public void update(Faculty newEntity){
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND)) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, newEntity.getBudgetVolume());
            preparedStatement.setInt(3, newEntity.getTotalVolume());
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
    public Faculty get(int entityId){
        Faculty newFaculty = new Faculty();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newFaculty.setId(resultSet.getInt("id"));
            newFaculty.setName(resultSet.getString("name"));
            newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
            newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return newFaculty;
    }

    @Override
    public Collection<Faculty> findAll(){
        Collection<Faculty> faculties = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Faculty newFaculty = new Faculty();
                newFaculty.setId(resultSet.getInt("id"));
                newFaculty.setName(resultSet.getString("name"));
                newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
                newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
                faculties.add(newFaculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException();
        }
        return faculties;
    }
}
