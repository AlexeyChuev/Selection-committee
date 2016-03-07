package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Administrator;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 3/7/2016.
 */
public class AdministratorRepository implements Repository<Administrator> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.ADMINISTRATOR (USER_ID) VALUES(?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.ADMINISTRATOR SET USER_ID=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.ADMINISTRATOR WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.ADMINISTRATOR WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ADMINISTRATOR";

    @Override
    public void create(Administrator entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND))
        {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public void update(Administrator newEntity) {
        if (get(newEntity.getId()) == null) throw new EntityNotExistsException();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMAND))
        {
            preparedStatement.setInt(1, newEntity.getUserId());
            preparedStatement.setInt(2, newEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
    }

    @Override
    public void delete(int entityId) {
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
    public Administrator get(int entityId) {
        Administrator newAdministrator = new Administrator();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newAdministrator.setId(resultSet.getInt("id"));
            newAdministrator.setUserId(resultSet.getInt("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return newAdministrator;
    }

    @Override
    public Collection<Administrator> findAll() {
        Collection<Administrator> administrators = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next())
            {
                Administrator newAdministrator = new Administrator();
                newAdministrator.setId(resultSet.getInt("id"));
                newAdministrator.setUserId(resultSet.getInt("user_id"));
                administrators.add(newAdministrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return administrators;
    }
}
