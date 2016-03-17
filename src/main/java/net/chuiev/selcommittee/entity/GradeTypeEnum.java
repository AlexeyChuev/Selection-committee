package net.chuiev.selcommittee.entity;

/**
 * GradeType enum. There are only two types of grade.
 *
 * @author Oleksii Chuiev
 *
 */
public enum GradeTypeEnum {
    EXAM, CERTIFICATE;

    public String getName() {
        return name().toLowerCase();
    }
}
