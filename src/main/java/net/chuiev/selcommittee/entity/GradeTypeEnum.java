package net.chuiev.selcommittee.entity;

/**
 * Created by Alex on 3/7/2016.
 */
public enum  GradeTypeEnum {
    EXAM, CERTIFICATE;

    public String getName() {
        return name().toLowerCase();
    }
}
