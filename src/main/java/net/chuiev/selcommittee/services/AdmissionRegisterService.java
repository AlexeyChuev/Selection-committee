package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.AdmissionRegister;
import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.exception.EntityNotExistsException;
import net.chuiev.selcommittee.repository.ConnectionCreator;

import java.sql.*;

/**
 * Created by Alex on 3/8/2016.
 */
public class AdmissionRegisterService {
    private static Connection connection = ConnectionCreator.getConnection();

    public final static String CALCULATE_SUM_OF_EXAM_GRADES = "SELECT sum(grade) AS 'sum' FROM ADMIN.SUBMISSION_SUBJECT " +
            "WHERE ADMIN.SUBMISSION_SUBJECT.GRADE_TYPE=1 AND ADMIN.SUBMISSION_SUBJECT.SUBMISSION_ID=" +
            "(SELECT ADMIN.SUBMISSION.ID FROM ADMIN.SUBMISSION WHERE ADMIN.SUBMISSION.ENROLLEE_ID=? AND ADMIN.SUBMISSION.FACULTY_ID=?)";

    public final static String CALCULATE_SUM_OF_CERTIFICATE_GRADES = "SELECT sum(grade)  AS 'sum' FROM ADMIN.SUBMISSION_SUBJECT " +
            "WHERE ADMIN.SUBMISSION_SUBJECT.GRADE_TYPE=2 AND ADMIN.SUBMISSION_SUBJECT.SUBMISSION_ID=" +
            "(SELECT ADMIN.SUBMISSION.ID FROM ADMIN.SUBMISSION WHERE ADMIN.SUBMISSION.ENROLLEE_ID=? AND ADMIN.SUBMISSION.FACULTY_ID=?)";

    public final static String GET_ENROLLEE_EMAIL = "SELECT Users.email AS 'email' FROM ADMIN.USERS WHERE ADMIN.USERS.ID=";

    public static short getEnrolleeExamSum(Enrollee enrollee, Faculty faculty)
    {
        short sum=0;
        try {
            PreparedStatement statement = connection.prepareStatement(CALCULATE_SUM_OF_EXAM_GRADES);
            statement.setInt(1, enrollee.getId());
            statement.setInt(2, faculty.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            sum = Short.parseShort(resultSet.getString("sum"));
            if(sum==0)throw new EntityNotExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return sum;
    }

    public static short getEnrolleeCertificateSum(Enrollee enrollee, Faculty faculty)
    {
        short sum=0;
        try {
            PreparedStatement statement = connection.prepareStatement(CALCULATE_SUM_OF_CERTIFICATE_GRADES);
            statement.setInt(1, enrollee.getId());
            statement.setInt(2, faculty.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.wasNull()) throw new EntityNotExistsException();
            sum = Short.parseShort(resultSet.getString("sum"));
            if(sum==0)throw new EntityNotExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return sum;
    }

    public static String getEmailEnrollee(Enrollee enrollee)
    {
        String email = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ENROLLEE_EMAIL + enrollee.getId());
            resultSet.next();
            email = resultSet.getString("email");
            if(email==null)throw new EntityNotExistsException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotExistsException(e);
        }
        return email;
    }

    public static AdmissionRegister createAdmissionRegister(Enrollee enrollee, Faculty faculty)
    {
        AdmissionRegister admissionRegister = new AdmissionRegister();
        admissionRegister.setEmail(getEmailEnrollee(enrollee));
        admissionRegister.setFullName(enrollee.getFullName());
        admissionRegister.setBlocked(enrollee.isBlocked());
        admissionRegister.setFacultyId(faculty.getId());
        short enrolleExamSum = getEnrolleeExamSum(enrollee,faculty);
        short enrolleCertificateSum = getEnrolleeCertificateSum(enrollee,faculty);
        int totalSum = enrolleExamSum+enrolleCertificateSum;
        admissionRegister.setCertificateSum(enrolleCertificateSum);
        admissionRegister.setExamsSum(enrolleExamSum);
        admissionRegister.setTotalSum((short) totalSum);
        return admissionRegister;
    }

}
