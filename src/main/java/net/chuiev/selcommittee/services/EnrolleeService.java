package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.*;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.FacultySubjectRepository;
import net.chuiev.selcommittee.repository.SubmissionRepository;

import java.sql.Blob;
import java.util.Collection;

/**
 * Created by Alex on 3/7/2016.
 */
public class EnrolleeService {
    private static EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
    private static SubmissionRepository submissionRepository = new SubmissionRepository();
    private static FacultySubjectRepository facultySubjectRepository = new FacultySubjectRepository();

    public static void registrationInSystem(String fullname, String city, String region, String schoolName, String email, boolean isBlocked, Blob certificate)
    {
        Enrollee enrollee = new Enrollee();
        enrollee.setFullName(fullname);
        enrollee.setCity(city);
        enrollee.setRegion(region);
        enrollee.setEmail(email);
        enrollee.setBlocked(isBlocked);
        enrollee.setSchoolName(schoolName);
        enrollee.setCertificate(certificate);
        enrolleeRepository.create(enrollee);
    }



    public static void registrationOnFaculty(Enrollee enrollee, Faculty faculty)
    {
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
        for(FacultySubject facultySubject:facultySubjects)
        {

        }


    }

    public static void addGrade(Submission submission, Subject subject)
    {

    }
}
