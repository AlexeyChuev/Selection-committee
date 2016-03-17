package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * FacultySubject entity. This transfer object characterized by id,
 * facultyId and subjectId.
 *
 * @author Oleksii Chuiev
 *
 */
public class FacultySubject implements Serializable {
    private static final long serialVersionUID = 2565574420335652970L;

    private int id;
    private int facultyId;
    private int subjectId;

    public FacultySubject(int id, int facultyId, int subjectId) {
        this.id = id;
        this.facultyId = facultyId;
        this.subjectId = subjectId;
    }

    public FacultySubject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "FacultySubject{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", subjectId=" + subjectId +
                '}';
    }
}
