package net.chuiev.selcommittee.entity;

import java.io.Serializable;
import java.sql.Blob;

/**
 * Enrollee entity. This transfer object characterized by id, fullName,
 * city, region, schoolName, userId.
 *
 * @author Oleksii Chuiev
 *
 */
public class Enrollee implements Serializable {
    private static final long serialVersionUID = 1113613887250453747L;

    private int id;
    private String fullName;
    private String city;
    private String region;
    private String schoolName;
    private int userId;

    public Enrollee(String fullName, String city, String region, String schoolName, int userId) {

        this.fullName = fullName;
        this.city = city;
        this.region = region;
        this.schoolName = schoolName;

        this.userId = userId;
    }

    public Enrollee() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
