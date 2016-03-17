package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.Role;
import net.chuiev.selcommittee.entity.RoleTypeEnum;
import net.chuiev.selcommittee.exception.UnmodifiableEntityException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Role Repository. Part of implemeting pattern Repository.
 * Give only FIND-operations with Role data.
 *
 * @author Oleksii Chuiev
 *
 */
public class RoleRepository implements Repository<Role> {
    private static final Logger LOG = Logger.getLogger(RoleRepository.class);

    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.ROLE WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ROLE";

    @Override
    public void create(Role entity) {
        LOG.error("entity unmodifiable");
        throw new UnmodifiableEntityException();
    }

    @Override
    public void update(Role newEntity) {
        LOG.error("entity unmodifiable");
        throw new UnmodifiableEntityException();
    }

    @Override
    public void delete(int entityId) {
        LOG.error("entity unmodifiable");
        throw new UnmodifiableEntityException();
    }

    @Override
    public Role get(int entityId) {
        Role newRole = new Role();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND+entityId);
            resultSet.next();
            newRole.setId(resultSet.getInt("id"));
            String tempRoleType = resultSet.getString("role_type");
            if (RoleTypeEnum.ADMIN.getName().equalsIgnoreCase(tempRoleType)) {
                newRole.setRoleType(RoleTypeEnum.ADMIN);
            } else {
                newRole.setRoleType(RoleTypeEnum.CLIENT);
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newRole;
    }

    @Override
    public Collection<Role> findAll() {
        Collection<Role> roles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                Role newRole = new Role();
                newRole.setId(resultSet.getInt("id"));
                String tempRoleType = resultSet.getString("role_type");
                if (RoleTypeEnum.ADMIN.getName().equalsIgnoreCase(tempRoleType)) {
                    newRole.setRoleType(RoleTypeEnum.ADMIN);
                } else {
                    newRole.setRoleType(RoleTypeEnum.CLIENT);
                }
                roles.add(newRole);
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e);
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return roles;
    }
}
