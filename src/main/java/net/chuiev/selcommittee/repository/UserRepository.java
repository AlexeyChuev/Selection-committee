package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.User;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 3/7/2016.
 */
public class UserRepository implements Repository<User> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.Users (role, email, password) VALUES(?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.Users SET role=?, email=?, password=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.Users WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.Users WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.Users";

    @Override
    public void create(User entity){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND)) {
            preparedStatement.setInt(1, entity.getRole());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public void update(User newEntity){
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND)) {
            preparedStatement.setInt(1, newEntity.getRole());
            preparedStatement.setString(2, newEntity.getEmail());
            preparedStatement.setString(3, newEntity.getPassword());
            preparedStatement.setInt(4, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
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
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public User get(int entityId){
        User newUser = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newUser.setId(resultSet.getInt("id"));
            newUser.setEmail(resultSet.getString("email"));
            newUser.setPassword(resultSet.getString("password"));
            newUser.setRole(resultSet.getInt("role"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return newUser;
    }

    @Override
    public Collection<User> findAll(){
        Collection<User> users = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                User newUser = new User();
                newUser.setId(resultSet.getInt("id"));
                newUser.setEmail(resultSet.getString("email"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setRole(resultSet.getInt("role"));
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return users;
    }
}
