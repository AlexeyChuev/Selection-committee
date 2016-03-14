package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.*;
import net.chuiev.selcommittee.repository.*;

import java.util.*;

/**
 * Created by Alex on 3/8/2016.
 */
public class AdmissionRegisterService {
    private EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
    private FacultyRepository facultyRepository = new FacultyRepository();

    private Collection<Enrollee> getFacultyEnrollees(Faculty faculty) {
        return enrolleeRepository.findFacultyEnrollees(faculty.getId());
    }

    private List<AdmissionRegisterRecord> createAdmissionRegisterRecords(Faculty faculty) {
        //final list of records for register
        List<AdmissionRegisterRecord> admissionRegisterRecordsList = new ArrayList<>();

        //list enrollees for current faculty
        List<Enrollee> enrollees = (List<Enrollee>) getFacultyEnrollees(faculty);

        for (Enrollee enrollee : enrollees) {
            //create new record
            AdmissionRegisterRecord admissionRegisterRecord = new AdmissionRegisterRecord();

            //add information about enrollee and faculty
            admissionRegisterRecord.setEnrollee(enrollee);
            admissionRegisterRecord.setFaculty(faculty);

            //find three submissionSubject for current faculty with their grades
            SubmissionSubjectRepository submissionSubjectRepository = new SubmissionSubjectRepository();
            List<SubmissionSubject> submissionSubjectsExams = (List<SubmissionSubject>) submissionSubjectRepository.findSubmissionSubjectsExam(faculty.getId(), enrollee.getId());

            //find subjects for current faculty
            FacultySubjectRepository facultySubjectRepository = new FacultySubjectRepository();
            List<FacultySubject> facultySubjects = (List<FacultySubject>) facultySubjectRepository.findSubjectsForFaculty(faculty);

            //find three already passed exams for current faculty and current enrollee
            SubjectRepository subjectRepository = new SubjectRepository();
            Subject exam1 = subjectRepository.get(submissionSubjectsExams.get(0).getSubjectId());
            Subject exam2 = subjectRepository.get(submissionSubjectsExams.get(1).getSubjectId());
            Subject exam3 = subjectRepository.get(submissionSubjectsExams.get(2).getSubjectId());
            int exam1Grade = submissionSubjectsExams.get(0).getGrade();
            int exam2Grade = submissionSubjectsExams.get(1).getGrade();
            int exam3Grade = submissionSubjectsExams.get(2).getGrade();

            //loop faculty subjects and add information about already filed submission
            //in this way we put exams and their grades in correct order into admissionRegisterRecord
            for (FacultySubject facultySubject : facultySubjects) {
                if (facultySubject.getSubjectId() == exam1.getId()) {
                    admissionRegisterRecord.setExam1(exam1);
                    admissionRegisterRecord.setExam1Grade(exam1Grade);
                }
                if (facultySubject.getSubjectId() == exam2.getId()) {
                    admissionRegisterRecord.setExam2(exam2);
                    admissionRegisterRecord.setExam2Grade(exam2Grade);
                }
                if (facultySubject.getSubjectId() == exam3.getId()) {
                    admissionRegisterRecord.setExam3(exam3);
                    admissionRegisterRecord.setExam3Grade(exam3Grade);
                }
            }

            //get submissionSubjects for certificate and their grades
            List<SubmissionSubject> submissionSubjectsCertificate = (List<SubmissionSubject>) submissionSubjectRepository.findSubmissionSubjectsCertificate(faculty.getId(), enrollee.getId());
            float sumOfCertificateGrades = 0;
            for (SubmissionSubject submissionSubject : submissionSubjectsCertificate) {
                sumOfCertificateGrades += submissionSubject.getGrade();
            }

            //calculate average mark of crtificate grades
            float certificateGPA = (sumOfCertificateGrades * 10 / 12) + 100;
            admissionRegisterRecord.setSummaryCertificateGrade(certificateGPA);

            //calculate total sum of grades for enrollee
            float totalExams = exam1Grade + exam2Grade + exam3Grade;
            float total = certificateGPA + totalExams;
            admissionRegisterRecord.setTotal(total);

            //add admissionRegisterRecord to list of of records for register
            admissionRegisterRecordsList.add(admissionRegisterRecord);
        }

        //sort admissionRegisterRecords List by total
        Collections.sort(admissionRegisterRecordsList, new Comparator<AdmissionRegisterRecord>() {
            @Override
            public int compare(AdmissionRegisterRecord o1, AdmissionRegisterRecord o2) {
                return Float.compare(o2.getTotal(), o1.getTotal());
            }
        });

        //add admissionState to each admissionRegisterRecord
        for (int i = 0; i < admissionRegisterRecordsList.size(); i++) {
            if (i < faculty.getBudgetVolume()) {
                admissionRegisterRecordsList.get(i).setAdmissionState("Поступил(ла) на бюджет");
                continue;
            }
            if (i < faculty.getTotalVolume()) {
                admissionRegisterRecordsList.get(i).setAdmissionState("Поступил(ла) на контракт");
                continue;
            }
            admissionRegisterRecordsList.get(i).setAdmissionState("Не поступил(ла)");
        }
        return admissionRegisterRecordsList;
    }

    public Map<Faculty, List<AdmissionRegisterRecord>> getFacultiesAndTheirAdmissions() {
        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = new HashMap<>();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();

        //for each faculty add list of admissionRegisterRecords in order by total sum
        for (Faculty faculty : faculties) {
            List<AdmissionRegisterRecord> admissionRegisterRecords = createAdmissionRegisterRecords(faculty);
            facultiesAndTheirAdmissions.put(faculty, admissionRegisterRecords);
        }
        return facultiesAndTheirAdmissions;
    }
}
