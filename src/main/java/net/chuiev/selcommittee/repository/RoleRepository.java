package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Role;
import net.chuiev.selcommittee.entity.RoleTypeEnum;
import net.chuiev.selcommittee.exception.EntityNotExistsException;
import net.chuiev.selcommittee.exception.UnmodifiableEntityException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 3/7/2016.
 */
public class RoleRepository implements Repository<Role> {
    private Connection connection = ConnectionCreator.getConnection();

    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.ROLE WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ROLE";

    @Override
    public void create(Role entity) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public void update(Role newEntity) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public void delete(int entityId) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public Role get(int entityId) {
        Role newRole = new Role();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newRole.setId(resultSet.getInt("id"));
            String tempRoleType = resultSet.getString("role_type");
            if (RoleTypeEnum.ADMIN.getName().equalsIgnoreCase(tempRoleType))
                newRole.setRoleType(RoleTypeEnum.ADMIN);
            else newRole.setRoleType(RoleTypeEnum.CLIENT);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return newRole;
    }

    @Override
    public Collection<Role> findAll() {
        Collection<Role> roles = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Role newRole = new Role();
                newRole.setId(resultSet.getInt("id"));
                String tempRoleType = resultSet.getString("role_type");
                if (RoleTypeEnum.ADMIN.getName().equalsIgnoreCase(tempRoleType))
                    newRole.setRoleType(RoleTypeEnum.ADMIN);
                else newRole.setRoleType(RoleTypeEnum.CLIENT);
                roles.add(newRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return roles;
    }
}
