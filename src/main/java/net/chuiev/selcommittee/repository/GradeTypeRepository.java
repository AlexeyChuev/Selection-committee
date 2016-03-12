package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.GradeType;
import net.chuiev.selcommittee.entity.GradeTypeEnum;
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
public class GradeTypeRepository implements Repository<GradeType> {
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.GRADE_TYPE WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.ROLE";


    @Override
    public void create(GradeType entity) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public void update(GradeType newEntity) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public void delete(int entityId) {
        throw new UnmodifiableEntityException();
    }

    @Override
    public GradeType get(int entityId) {
        GradeType newGradeType = new GradeType();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND);
            resultSet.next();
            newGradeType.setId(resultSet.getInt("id"));
            String tempRoleType = resultSet.getString("grade_type");
            if (GradeTypeEnum.EXAM.getName().equalsIgnoreCase(tempRoleType))
                newGradeType.setGradeType(GradeTypeEnum.EXAM);
            else newGradeType.setGradeType(GradeTypeEnum.CERTIFICATE);
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newGradeType;
    }

    @Override
    public Collection<GradeType> findAll() {
        Collection<GradeType> gradeTypes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                GradeType newGradeType = new GradeType();
                newGradeType.setId(resultSet.getInt("id"));
                String tempRoleType = resultSet.getString("grade_type");
                if (GradeTypeEnum.EXAM.getName().equalsIgnoreCase(tempRoleType))
                    newGradeType.setGradeType(GradeTypeEnum.EXAM);
                else newGradeType.setGradeType(GradeTypeEnum.CERTIFICATE);
                gradeTypes.add(newGradeType);
            }
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return gradeTypes;
    }
}
