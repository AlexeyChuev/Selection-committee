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
    private Connection connection = ConnectionCreator.getConnection();

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
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND);
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            newGradeType.setId(resultSet.getInt("id"));
            String tempRoleType = resultSet.getString("grade_type");
            if (GradeTypeEnum.EXAM.getName().equalsIgnoreCase(tempRoleType))
                newGradeType.setGradeType(GradeTypeEnum.EXAM);
            else newGradeType.setGradeType(GradeTypeEnum.CERTIFICATE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return newGradeType;
    }

    @Override
    public Collection<GradeType> findAll() {
        Collection<GradeType> gradeTypes = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COMMAND);
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
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return gradeTypes;
    }
}
