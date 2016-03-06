package net.chuiev.selcommittee.entity;

import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by Алексей on 3/5/2016.
 */
public class Enrollee implements Serializable{
    private static final long serialVersionUID = 1113613887250453747L;

    private int id;
    private String firstname;
    private String lastname;
    private String surname;
    private String city;
    private String region;
    private String schoolName;
    private boolean isBlocked;
    private Blob certificate; //?????????????

    public Enrollee(int id, String firstname, String lastname, String surname, String city, String region, String schoolName, boolean isBlocked, Blob certificate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.city = city;
        this.region = region;
        this.schoolName = schoolName;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", isBlocked=" + isBlocked +
                ", certificate=" + certificate +
                '}';
    }
}
