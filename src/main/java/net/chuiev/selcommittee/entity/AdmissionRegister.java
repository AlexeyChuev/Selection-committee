package net.chuiev.selcommittee.entity;

/**
 * Created by Алексей on 3/6/2016.
 */
public class AdmissionRegister{

    private int facultyId;
    private String fullName;
    private String email;
    private boolean isBlocked;
    private short examsSum;
    private short certificateSum;
    private short totalSum;

    public AdmissionRegister(int facultyId, String fullName, String email, boolean isBlocked, short examsSum, short diplomaSum, short totalSum) {
        this.facultyId = facultyId;
        this.fullName = fullName;
        this.email = email;
        this.isBlocked = isBlocked;
        this.examsSum = examsSum;
        this.certificateSum = diplomaSum;
        this.totalSum = totalSum;
    }

    public AdmissionRegister(){}

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

    public short getExamsSum() {
        return examsSum;
    }

    public void setExamsSum(short examsSum) {
        this.examsSum = examsSum;
    }

    public short getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(short totalSum) {
        this.totalSum = totalSum;
    }

    public short getCertificateSum() {
        return certificateSum;
    }

    public void setCertificateSum(short certificateSum) {
        this.certificateSum = certificateSum;
    }

    @Override
    public String toString() {
        return "AdmissionRegister{" +
                "facultyId=" + facultyId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", isBlocked=" + isBlocked +
                ", examsSum=" + examsSum +
                ", certificateSum=" + certificateSum +
                ", totalSum=" + totalSum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdmissionRegister that = (AdmissionRegister) o;

        if (facultyId != that.facultyId) return false;
        if (isBlocked != that.isBlocked) return false;
        if (examsSum != that.examsSum) return false;
        if (certificateSum != that.certificateSum) return false;
        if (totalSum != that.totalSum) return false;
        if (!fullName.equals(that.fullName)) return false;
        return email.equals(that.email);

    }

    @Override
    public int hashCode() {
        int result = facultyId;
        result = 31 * result + fullName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + (int) examsSum;
        result = 31 * result + (int) certificateSum;
        result = 31 * result + (int) totalSum;
        return result;
    }
}
