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
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.FACULTY WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.FACULTY";

    private final static String SORT_BY_NAME_FROM_A_TO_Z = "SELECT * FROM ADMIN.FACULTY ORDER BY NAME ASC";
    private final static String SORT_BY_NAME_FROM_Z_TO_A = "SELECT * FROM ADMIN.FACULTY ORDER BY NAME DESC";
    private final static String SORT_BY_BUDGETVOLUME = "SELECT * FROM ADMIN.FACULTY ORDER BY BUDGETVOLUME DESC";
    private final static String SORT_BY_TOTALVOLUME = "SELECT * FROM ADMIN.FACULTY ORDER BY TOTALVOLUME DESC";

    private final static String FIND_BY_NAME = "SELECT * FROM ADMIN.FACULTY WHERE NAME=";

    @Override
    public void create(Faculty entity){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMAND)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getBudgetVolume());
            preparedStatement.setInt(3, entity.getTotalVolume());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

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
    public Faculty get(int entityId){
        Faculty newFaculty = new Faculty();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            resultSet.next();

            newFaculty.setId(resultSet.getInt("id"));
            newFaculty.setName(resultSet.getString("name"));
            newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
            newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
        } catch (SQLException e) {
            e.printStackTrace();

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

        }
        return faculties;
    }

    private Collection<Faculty> sortFaculties(String sql)
    {
        Collection<Faculty> faculties = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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

        }
        return faculties;
    }

    public  Collection<Faculty> sortedFacultiesByNameFromAToZ()
    {
        return new FacultyRepository().sortFaculties(SORT_BY_NAME_FROM_A_TO_Z);
    }

    public  Collection<Faculty> sortedFacultiesByNameFromZToA()
    {
        return new FacultyRepository().sortFaculties(SORT_BY_NAME_FROM_Z_TO_A);
    }

    public  Collection<Faculty> sortedFacultiesByBudgetVolume()
    {
        return new FacultyRepository().sortFaculties(SORT_BY_BUDGETVOLUME);
    }

    public  Collection<Faculty> sortedFacultiesByTotalVolume()
    {
        return new FacultyRepository().sortFaculties(SORT_BY_TOTALVOLUME);
    }


    public Faculty findFacultyByName(String name){
        Faculty newFaculty = new Faculty();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_BY_NAME + prepareName(name));
            if(!resultSet.next()) return null;
            newFaculty.setId(resultSet.getInt("id"));
            newFaculty.setName(resultSet.getString("name"));
            newFaculty.setBudgetVolume(resultSet.getInt("budgetVolume"));
            newFaculty.setTotalVolume(resultSet.getInt("totalVolume"));
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return newFaculty;
    }

    private static String prepareName(String name)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("'");
        sb.append(name);
        sb.append("'");
        return sb.toString();
    }
}
