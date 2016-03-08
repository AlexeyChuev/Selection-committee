package net.chuiev.selcommittee.services;

import net.chuiev.selcommittee.entity.Enrollee;
import net.chuiev.selcommittee.entity.Faculty;
import net.chuiev.selcommittee.repository.EnrolleeRepository;
import net.chuiev.selcommittee.repository.FacultyRepository;

/**
 * Created by Alex on 3/7/2016.
 */
public class AdminService {
    private static FacultyRepository facultyRepository = new FacultyRepository();
    private static EnrolleeRepository enrolleeRepository = new EnrolleeRepository();

    public static void addFaculty(Faculty faculty) {
        facultyRepository.create(faculty);
    }

    public static void editingFaculty(Faculty faculty) {
        facultyRepository.update(faculty);
    }

    public static void removeFaculty(Faculty faculty) {
        facultyRepository.delete(faculty.getId());
    }

    public static void changeEnrolleeBlockingStatus(Enrollee enrollee, boolean isBlocked) {
        enrolleeRepository.updateStatusIsBlocked(enrollee, isBlocked);
    }


}
