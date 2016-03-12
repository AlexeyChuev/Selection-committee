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
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.Users (role, email, password, ISBLOCKED) VALUES(?,?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.Users SET role=?, email=?, password=?, ISBLOCKED=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.Users WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.Users WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.Users";

    private final static String FIND_USER_BY_EMAIL = "SELECT * FROM ADMIN.Users WHERE EMAIL=";


    @Override
    public void create(User entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setInt(1, entity.getRole());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setBoolean(4, entity.isBlocked());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public void update(User newEntity) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setInt(1, newEntity.getRole());
            preparedStatement.setString(2, newEntity.getEmail());
            preparedStatement.setString(3, newEntity.getPassword());
            preparedStatement.setBoolean(4, newEntity.isBlocked());
            preparedStatement.setInt(5, newEntity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public void delete(int entityId) {
        if (get(entityId) == null) throw new EntityNotExistsException();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_COMMAND + entityId);
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
        }
    }

    @Override
    public User get(int entityId) {
        User newUser = new User();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            if (!resultSet.next()) return null;
            newUser.setId(resultSet.getInt("id"));
            newUser.setEmail(resultSet.getString("email"));
            newUser.setPassword(resultSet.getString("password"));
            newUser.setRole(resultSet.getInt("role"));
            newUser.setBlocked(resultSet.getBoolean("isblocked"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newUser;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                User newUser = new User();
                newUser.setId(resultSet.getInt("id"));
                newUser.setEmail(resultSet.getString("email"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setRole(resultSet.getInt("role"));
                newUser.setBlocked(resultSet.getBoolean("isblocked"));
                users.add(newUser);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return users;
    }

    public User findUserByEmail(String email) {
        User newUser = new User();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_USER_BY_EMAIL + prepareEmail(email));
            if (!resultSet.next()) return null;
            newUser.setId(resultSet.getInt("id"));
            newUser.setEmail(resultSet.getString("email"));
            newUser.setPassword(resultSet.getString("password"));
            newUser.setRole(resultSet.getInt("role"));
            newUser.setBlocked(resultSet.getBoolean("isblocked"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newUser;
    }

    private static String prepareEmail(String email) {
        StringBuffer sb = new StringBuffer();
        sb.append("'");
        sb.append(email);
        sb.append("'");
        return sb.toString();
    }

}
