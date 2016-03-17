package net.chuiev.selcommittee.entity;

import java.io.Serializable;

/**
 * GradeType entity. This transfer object characterized
 * by id, gradeType (there are only two types of grade).
 *
 * @author Oleksii Chuiev
 *
 */
public class GradeType implements Serializable {
    private static final long serialVersionUID = 4113633887252453777L;

    private int id;
    private Enum gradeType;

    public GradeType(int id, Enum gradeType) {
        this.id = id;
        this.gradeType = gradeType;
    }

    public GradeType() {
    }

    public Enum getGradeType() {
        return gradeType;
    }

    public void setGradeType(Enum gradeType) {
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
