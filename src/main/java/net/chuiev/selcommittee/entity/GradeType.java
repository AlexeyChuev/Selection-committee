package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * Created by Alex on 3/7/2016.
 */
public class GradeType implements Serializable{
    private static final long serialVersionUID = 4113633887252453777L;

    private int id;
    private String gradeType;

    public GradeType(int id, String gradeType) {
        this.id = id;
        this.gradeType = gradeType;
    }

    public GradeType(){}

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GradeType{" +
                "id=" + id +
                ", gradeType='" + gradeType + '\'' +
                '}';
    }
}
