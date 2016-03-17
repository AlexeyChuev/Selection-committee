package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Submission entity. This transfer object characterized by id,
 * facultyId and enrolleeId.
 *
 * @author Oleksii Chuiev
 *
 */
public class Submission implements Serializable {
    private static final long serialVersionUID = -5388561545513613948L;

    private int id;
    private int facultyId;
    private int enrolleeId;

    public Submission(int id, int facultyId, int enrolleeId) {
        this.id = id;
        this.facultyId = facultyId;
        this.enrolleeId = enrolleeId;
    }

    public Submission() {
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

    public int getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(int enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", enrolleeId=" + enrolleeId +
                '}';
    }
}
