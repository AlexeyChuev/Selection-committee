package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.*;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.FacultySubjectRepository;
import net.chuiev.selcommittee.repository.SubmissionRepository;
import net.chuiev.selcommittee.repository.UserRepository;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alex on 3/7/2016.
 */
public class EnrolleeService {
    private static EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
    private static SubmissionRepository submissionRepository = new SubmissionRepository();
    private static FacultySubjectRepository facultySubjectRepository = new FacultySubjectRepository();
    private static UserRepository userRepository = new UserRepository();

    public static void registrationEnrolleInSystem(String email, String password, String fullname,
                                                   String city, String region, String schoolName,
                                                   Blob certificate) {
        UserService.registrationUserInSystem(email, password, "client");

        User justAddedUser = userRepository.findUserByEmail(email);

        Enrollee enrollee = new Enrollee();
        enrollee.setFullName(fullname);
        enrollee.setCity(city);
        enrollee.setRegion(region);
        enrollee.setUserId(justAddedUser.getId());

        enrollee.setSchoolName(schoolName);

        enrolleeRepository.create(enrollee);
    }


    public static void registrationOnFaculty(Enrollee enrollee, Faculty faculty) {
        //create new submission
        Submission newSubmission = new Submission();
        newSubmission.setEnrolleeId(enrollee.getId());
        newSubmission.setFacultyId(faculty.getId());
        submissionRepository.create(newSubmission);

        //get Submission, that has been created above
        Submission enrolleSubmission = submissionRepository.getByFacultyAndEnrolle(faculty, enrollee);

        //get subjects for faculty (special exams)
        Collection<FacultySubject> facultySubjects = facultySubjectRepository.findSubjectsForFaculty(faculty);

        //add submission_subjects for special exams
        for (FacultySubject facultySubject : facultySubjects) {
            SubmissionSubject specialSubmissionSubject = new SubmissionSubject();
            specialSubmissionSubject.setSubjectId(facultySubject.getSubjectId());
            specialSubmissionSubject.setSubmissionId(enrolleSubmission.getId());

        }


    }

    //get them from page with special textBox for each grade in
    public static Collection<Integer> getGradesForSpecialExams(int firstGrade, int secondGrade, int thirdGrade) {
        Collection<Integer> gradesForSpecialExams = new ArrayList<>();
        gradesForSpecialExams.add(firstGrade);
        gradesForSpecialExams.add(secondGrade);
        gradesForSpecialExams.add(thirdGrade);
        return gradesForSpecialExams;
    }

    public static Collection<Integer> getCertificateGrades(int maths, int history, int geography, int chemistry, int physics,
                                                           int english, int ukrainian, int biology, int computer_science, int economics) {
        Collection<Integer> certificateGrades = new ArrayList<>();
        certificateGrades.add(maths);
        certificateGrades.add(history);
        certificateGrades.add(geography);
        certificateGrades.add(chemistry);
        certificateGrades.add(physics);
        certificateGrades.add(english);
        certificateGrades.add(ukrainian);
        certificateGrades.add(biology);
        certificateGrades.add(computer_science);
        certificateGrades.add(economics);
        return certificateGrades;
    }


    /*public static void addGrade(Submission submission, Subject subject) {

    }*/
}
