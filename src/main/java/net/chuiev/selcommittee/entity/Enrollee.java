package net.chuiev.selcommittee.entity;

import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by Алексей on 3/5/2016.
 */
public class Enrollee implements Serializable{
    private static final long serialVersionUID = 1113613887250453747L;

    private int id;
    private String fullName;
    private String city;
    private String region;
    private String schoolName;
    private String email;
    private boolean isBlocked;
    private Blob certificate; //?????????????

    public Enrollee(int id, String fullName, String city, String region, String schoolName, String email, boolean isBlocked, Blob certificate) {
        this.id = id;
        this.fullName = fullName;
        this.city = city;
        this.region = region;
        this.schoolName = schoolName;
        this.email = email;
        this.isBlocked = isBlocked;
        this.certificate = certificate;
    }

    public Enrollee(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Blob getCertificate() {
        return certificate;
    }

    public void setCertificate(Blob certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", email='" + email + '\'' +
                ", isBlocked=" + isBlocked +
                ", certificate=" + certificate +
                '}';
    }
}
