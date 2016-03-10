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

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.Users (role, email, password, ISBLOCKED) VALUES(?,?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.Users SET role=?, email=?, password=?, ISBLOCKED=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.Users WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.Users WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.Users";

    private final static String FIND_USER_BY_EMAIL = "SELECT * FROM ADMIN.Users WHERE EMAIL=";


    @Override
    public void create(User entity){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND)) {
            preparedStatement.setInt(1, entity.getRole());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setBoolean(4, entity.isBlocked());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(User newEntity){
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND)) {
            preparedStatement.setInt(1, newEntity.getRole());
            preparedStatement.setString(2, newEntity.getEmail());
            preparedStatement.setString(3, newEntity.getPassword());
            preparedStatement.setBoolean(4, newEntity.isBlocked());
            preparedStatement.setInt(5, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

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

        }
    }

    @Override
    public User get(int entityId){
        User newUser = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();

            newUser.setId(resultSet.getInt("id"));
            newUser.setEmail(resultSet.getString("email"));
            newUser.setPassword(resultSet.getString("password"));
            newUser.setRole(resultSet.getInt("role"));
            newUser.setBlocked(resultSet.getBoolean("isblocked"));
        } catch (SQLException e) {
            e.printStackTrace();

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
                newUser.setBlocked(resultSet.getBoolean("isblocked"));
                users.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return users;
    }

    public User findUserByEmail(String email){
        User newUser = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_USER_BY_EMAIL + prepareEmail(email));
            if(!resultSet.next()) return null;
            newUser.setId(resultSet.getInt("id"));
            newUser.setEmail(resultSet.getString("email"));
            newUser.setPassword(resultSet.getString("password"));
            newUser.setRole(resultSet.getInt("role"));
            newUser.setBlocked(resultSet.getBoolean("isblocked"));
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return newUser;
    }

    private static String prepareEmail(String email)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("'");
        sb.append(email);
        sb.append("'");
        return sb.toString();
    }



    /*public boolean getBlockedStatusByUserId(int userId) {
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ENROLLEE_BY_USER_ID + userId);
            resultSet.next();

            result = resultSet.getBoolean("isBlocked");
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }*/
}
