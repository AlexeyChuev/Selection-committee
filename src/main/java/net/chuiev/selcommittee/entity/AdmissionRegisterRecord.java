package net.chuiev.selcommittee.entity;

/**
 * Created by Алексей on 3/6/2016.
 */
public class AdmissionRegisterRecord {

    private Faculty faculty;
    private Enrollee enrollee;
    private float summaryCertificateGrade;
    private Subject exam1;
    private int exam1Grade;
    private Subject exam2;
    private int exam2Grade;
    private Subject exam3;
    private int exam3Grade;
    private float total;
    private String admissionState;

    public AdmissionRegisterRecord(Faculty faculty, Enrollee enrollee, float summaryCertificateGrade, Subject exam1, int exam1Grade,
                                   Subject exam2, int exam2Grade, Subject exam3, int exam3Grade, float total, String admissionState) {
        this.faculty = faculty;
        this.enrollee = enrollee;
        this.summaryCertificateGrade = summaryCertificateGrade;
        this.exam1 = exam1;
        this.exam1Grade = exam1Grade;
        this.exam2 = exam2;
        this.exam2Grade = exam2Grade;
        this.exam3 = exam3;
        this.exam3Grade = exam3Grade;
        this.total = total;
        this.admissionState = admissionState;
    }

    public AdmissionRegisterRecord() {
    }

    public String getAdmissionState() {
        return admissionState;
    }

    public void setAdmissionState(String admissionState) {
        this.admissionState = admissionState;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public float getSummaryCertificateGrade() {
        return summaryCertificateGrade;
    }

    public void setSummaryCertificateGrade(float summaryCertificateGrade) {
        this.summaryCertificateGrade = summaryCertificateGrade;
    }

    public Subject getExam1() {
        return exam1;
    }

    public void setExam1(Subject exam1) {
        this.exam1 = exam1;
    }

    public int getExam1Grade() {
        return exam1Grade;
    }

    public void setExam1Grade(int exam1Grade) {
        this.exam1Grade = exam1Grade;
    }

    public Subject getExam2() {
        return exam2;
    }

    public void setExam2(Subject exam2) {
        this.exam2 = exam2;
    }

    public int getExam2Grade() {
        return exam2Grade;
    }

    public void setExam2Grade(int exam2Grade) {
        this.exam2Grade = exam2Grade;
    }

    public Subject getExam3() {
        return exam3;
    }

    public void setExam3(Subject exam3) {
        this.exam3 = exam3;
    }

    public int getExam3Grade() {
        return exam3Grade;
    }

    public void setExam3Grade(int exam3Grade) {
        this.exam3Grade = exam3Grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdmissionRegisterRecord that = (AdmissionRegisterRecord) o;

        if (Float.compare(that.summaryCertificateGrade, summaryCertificateGrade) != 0) return false;
        if (exam1Grade != that.exam1Grade) return false;
        if (exam2Grade != that.exam2Grade) return false;
        if (exam3Grade != that.exam3Grade) return false;
        if (Float.compare(that.total, total) != 0) return false;
        if (!faculty.equals(that.faculty)) return false;
        if (!enrollee.equals(that.enrollee)) return false;
        if (!exam1.equals(that.exam1)) return false;
        if (!exam2.equals(that.exam2)) return false;
        if (!exam3.equals(that.exam3)) return false;
        return admissionState.equals(that.admissionState);

    }

    @Override
    public int hashCode() {
        int result = faculty.hashCode();
        result = 31 * result + enrollee.hashCode();
        result = 31 * result + (summaryCertificateGrade != +0.0f ? Float.floatToIntBits(summaryCertificateGrade) : 0);
        result = 31 * result + exam1.hashCode();
        result = 31 * result + exam1Grade;
        result = 31 * result + exam2.hashCode();
        result = 31 * result + exam2Grade;
        result = 31 * result + exam3.hashCode();
        result = 31 * result + exam3Grade;
        result = 31 * result + (total != +0.0f ? Float.floatToIntBits(total) : 0);
        result = 31 * result + admissionState.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AdmissionRegisterRecord{" +
                "faculty=" + faculty +
                ", enrollee=" + enrollee +
                ", summaryCertificateGrade=" + summaryCertificateGrade +
                ", exam1=" + exam1 +
                ", exam1Grade=" + exam1Grade +
                ", exam2=" + exam2 +
                ", exam2Grade=" + exam2Grade +
                ", exam3=" + exam3 +
                ", exam3Grade=" + exam3Grade +
                ", total=" + total +
                ", admissionState='" + admissionState + '\'' +
                '}';
    }
}
