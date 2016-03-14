package net.chuiev.selcommittee.repository;

import net.chuiev.selcommittee.entity.SubmissionSubject;
import net.chuiev.selcommittee.exception.EntityNotExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionSubjectRepository implements Repository<SubmissionSubject> {
    private ConnectionCreator connectionCreator = new ConnectionCreator();

    private final static String INSERT_COMMAND = "INSERT INTO ADMIN.SUBMISSION_SUBJECT (SUBMISSION_ID, SUBJECT_ID, GRADE, GRADE_TYPE) VALUES(?,?,?,?)";
    private final static String UPDATE_COMMAND = "UPDATE ADMIN.SUBMISSION_SUBJECT SET SUBMISSION_ID=?, SUBJECT_ID=?, grade=?, GRADE_TYPE=? WHERE id=?";
    private final static String DELETE_COMMAND = "DELETE FROM ADMIN.SUBMISSION_SUBJECT WHERE id=";
    private final static String FIND_COMMAND = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT WHERE id=";
    private final static String FIND_ALL_COMMAND = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT";

    private final static String FIND_SUBMISSION_SUBJECTS_EXAMS = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT WHERE SUBMISSION_ID IN (SELECT (id) FROM ADMIN.SUBMISSION WHERE FACULTY_ID=? AND ENROLLEE_ID=?) AND GRADE_TYPE=1";
    private final static String FIND_SUBMISSION_SUBJECTS_CERTIFICATE = "SELECT * FROM ADMIN.SUBMISSION_SUBJECT WHERE SUBMISSION_ID IN (SELECT (id) FROM ADMIN.SUBMISSION WHERE FACULTY_ID=? AND ENROLLEE_ID=?) AND GRADE_TYPE=2";

    @Override
    public void create(SubmissionSubject entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            preparedStatement.setInt(1, entity.getSubmissionId());
            preparedStatement.setInt(2, entity.getSubjectId());
            preparedStatement.setInt(3, entity.getGrade());
            preparedStatement.setInt(4, entity.getGradeType());
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
    public void update(SubmissionSubject newEntity) {
        if (get(newEntity.getId()) == null) {
            throw new EntityNotExistsException();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMMAND);
            preparedStatement.setInt(1, newEntity.getSubmissionId());
            preparedStatement.setInt(2, newEntity.getSubjectId());
            preparedStatement.setInt(3, newEntity.getGrade());
            preparedStatement.setInt(4, newEntity.getGradeType());
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
        if (get(entityId) == null) {
            throw new EntityNotExistsException();
        }
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
    public SubmissionSubject get(int entityId) {
        SubmissionSubject newSubmissionSubject = new SubmissionSubject();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_COMMAND + entityId);
            if (!resultSet.next()) return null;
            newSubmissionSubject.setId(resultSet.getInt("id"));
            newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
            newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
            newSubmissionSubject.setGrade(resultSet.getInt("grade"));
            newSubmissionSubject.setGradeType(resultSet.getInt("grade_type"));
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return newSubmissionSubject;
    }

    @Override
    public Collection<SubmissionSubject> findAll() {
        Collection<SubmissionSubject> submissionSubjects = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_COMMAND);
            while (resultSet.next()) {
                SubmissionSubject newSubmissionSubject = new SubmissionSubject();
                newSubmissionSubject.setId(resultSet.getInt("id"));
                newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
                newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                newSubmissionSubject.setGrade(resultSet.getInt("grade"));
                newSubmissionSubject.setGradeType(resultSet.getInt("grade_type"));
                submissionSubjects.add(newSubmissionSubject);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(statement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return submissionSubjects;
    }


    public Collection<SubmissionSubject> findSubmissionSubjectsExam(int facultyId, int enrolleeId) {
        Collection<SubmissionSubject> submissionSubjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SUBMISSION_SUBJECTS_EXAMS);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.setInt(2, enrolleeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubmissionSubject newSubmissionSubject = new SubmissionSubject();
                newSubmissionSubject.setId(resultSet.getInt("id"));
                newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
                newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                newSubmissionSubject.setGrade(resultSet.getInt("grade"));
                newSubmissionSubject.setGradeType(resultSet.getInt("grade_type"));
                submissionSubjects.add(newSubmissionSubject);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return submissionSubjects;
    }

    public Collection<SubmissionSubject> findSubmissionSubjectsCertificate(int facultyId, int enrolleeId) {
        Collection<SubmissionSubject> submissionSubjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionCreator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SUBMISSION_SUBJECTS_CERTIFICATE);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.setInt(2, enrolleeId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubmissionSubject newSubmissionSubject = new SubmissionSubject();
                newSubmissionSubject.setId(resultSet.getInt("id"));
                newSubmissionSubject.setSubmissionId(resultSet.getInt("SUBMISSION_ID"));
                newSubmissionSubject.setSubjectId(resultSet.getInt("SUBJECT_ID"));
                newSubmissionSubject.setGrade(resultSet.getInt("grade"));
                newSubmissionSubject.setGradeType(resultSet.getInt("grade_type"));
                submissionSubjects.add(newSubmissionSubject);
            }
            connection.commit();
        } catch (SQLException e) {
            connectionCreator.rollback(connection);
        } finally {
            connectionCreator.close(preparedStatement);
            connectionCreator.close(connection);
            connectionCreator.close(resultSet);
        }
        return submissionSubjects;
    }
}
