package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.*;
import net.chuiev.selcommittee.repository.*;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Service which produces AdmissionRegister. This is final
 * register with enrollees submissions result. Each record
 * in admissionRegisterRecordsList has some fields, that needed
 * to create table with enrollees (in order by total sum of
 * points - certificate + exam).
 *
 * @author Oleksii Chuiev
 *
 */
public class AdmissionRegisterService {
    private static final Logger LOG = Logger.getLogger(AdmissionRegisterService.class);

    private EnrolleeRepository enrolleeRepository = new EnrolleeRepository();
    private FacultyRepository facultyRepository = new FacultyRepository();

    /**
     * Method return all enrollees, that applyed to given faculty.
     * @param faculty
     *            Given faculty.
     * @return List of enrollees.
     */
    private Collection<Enrollee> getFacultyEnrollees(Faculty faculty) {
        LOG.debug("Start to looking for enrollees for faculty " + faculty);
        return enrolleeRepository.findFacultyEnrollees(faculty.getId());
    }

    /**
     * Method creates AdmissionRegisterRecords and put them into List.
     *
     * @param faculty
     *            Given faculty.
     * @return List of AdmissionRegisterRecord.
     */
    private List<AdmissionRegisterRecord> createAdmissionRegisterRecords(Faculty faculty) {
        LOG.debug("Start to making admissionRegisterRecordsList");
        //final list of records for register
        List<AdmissionRegisterRecord> admissionRegisterRecordsList = new ArrayList<>();

        //list enrollees for current faculty
        List<Enrollee> enrollees = (List<Enrollee>) getFacultyEnrollees(faculty);
        LOG.trace("Faculty enrollees " + enrollees);

        for (Enrollee enrollee : enrollees) {
            //create new record
            AdmissionRegisterRecord admissionRegisterRecord = new AdmissionRegisterRecord();

            //add information about enrollee and faculty
            admissionRegisterRecord.setEnrollee(enrollee);
            LOG.trace("enrollee =  " + enrollee);
            admissionRegisterRecord.setFaculty(faculty);
            LOG.trace("faculty =  " + faculty);

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
            LOG.trace("faculty subjects exams added " + facultySubjects);

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
            LOG.trace("total point =  " + total);
            admissionRegisterRecord.setTotal(total);

            //add admissionRegisterRecord to list of of records for register
            LOG.trace("admissionRegisterRecord was added " + admissionRegisterRecord);
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
                admissionRegisterRecordsList.get(i).setAdmissionState("Accepted on a budget");
                continue;
            }
            if (i < faculty.getTotalVolume()) {
                admissionRegisterRecordsList.get(i).setAdmissionState("Accepted on a contract");
                continue;
            }
            admissionRegisterRecordsList.get(i).setAdmissionState("Not accepted");
        }

        LOG.debug("Finish making admissionRegisterRecordsList");
        return admissionRegisterRecordsList;
    }

    /**
     * Method returns map, in which key = faculty
     * and value = list of AdmissionRegisterRecords

     * @return Map of faculties and their list of AdmissionRegisterRecords
     */
    public Map<Faculty, List<AdmissionRegisterRecord>> getFacultiesAndTheirAdmissions() {
        LOG.debug("Start forming map with faculties and admissionRegisterRecordsLists");

        Map<Faculty, List<AdmissionRegisterRecord>> facultiesAndTheirAdmissions = new HashMap<>();
        List<Faculty> faculties = (List<Faculty>) facultyRepository.findAll();

        //for each faculty add list of admissionRegisterRecords in order by total sum
        for (Faculty faculty : faculties) {
            List<AdmissionRegisterRecord> admissionRegisterRecords = createAdmissionRegisterRecords(faculty);
            facultiesAndTheirAdmissions.put(faculty, admissionRegisterRecords);
        }

        LOG.debug("Finish forming map with faculties and admissionRegisterRecordsLists");
        return facultiesAndTheirAdmissions;
    }
}
