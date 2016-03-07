package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Алексей on 3/5/2016.
 */
public class SubmissionSubject implements Serializable {
    private static final long serialVersionUID = -6889036256149495388L;

    private int id;
    private int submissionId;
    private int subjectId;
    private int grade;
    private int gradeType;

    public SubmissionSubject(int id, int submissionId, int subjectId, int grade, int gradeType) {
        this.id = id;
        this.submissionId = submissionId;
        this.subjectId = subjectId;
        this.grade = grade;
        this.gradeType=gradeType;
    }

    public SubmissionSubject(){}

    public int getGradeType() {
        return gradeType;
    }

    public void setGradeType(int gradeType) {
        this.gradeType = gradeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SubmissionSubject{" +
                "id=" + id +
                ", submissionId=" + submissionId +
                ", subjectId=" + subjectId +
                ", grade=" + grade +
                ", gradeType=" + gradeType +
                '}';
    }
}
