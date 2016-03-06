package net.chuiev.selcommittee.entity;

/**
 * Created by Алексей on 3/6/2016.
 */
public class AdmissionRegister{

    private int facultyId;
    private String fullName;
    private String email;
    private boolean isBlocked;
    private short preliminarySum;
    private short diplomaSum;
    private short totalSum;

    public AdmissionRegister(int facultyId, String fullName, String email, boolean isBlocked, short preliminarySum, short diplomaSum, short totalSum) {
        this.facultyId = facultyId;
        this.fullName = fullName;
        this.email = email;
        this.isBlocked = isBlocked;
        this.preliminarySum = preliminarySum;
        this.diplomaSum = diplomaSum;
        this.totalSum = totalSum;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public short getPreliminarySum() {
        return preliminarySum;
    }

    public void setPreliminarySum(short preliminarySum) {
        this.preliminarySum = preliminarySum;
    }

    public short getDiplomaSum() {
        return diplomaSum;
    }

    public void setDiplomaSum(short diplomaSum) {
        this.diplomaSum = diplomaSum;
    }

    public short getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(short totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "AdmissionRegister{" +
                "facultyId=" + facultyId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", isBlocked=" + isBlocked +
                ", preliminarySum=" + preliminarySum +
                ", diplomaSum=" + diplomaSum +
                ", totalSum=" + totalSum +
                '}';
    }
}
