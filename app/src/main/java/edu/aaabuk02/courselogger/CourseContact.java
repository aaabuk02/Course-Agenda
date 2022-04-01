package edu.aaabuk02.courselogger;

public class CourseContact  {
    private int courseContactID;
    private String courseName;
    private String courseDesc;
    private String studyHourTarget;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public CourseContact() {
       courseContactID = -1;
    }

    public int getCourseContactID() {
        return courseContactID;
    }

    public void setCourseContactID(int courseContactID) {
        this.courseContactID = courseContactID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getStudyHourTarget() {
        return studyHourTarget;
    }

    public void setStudyHourTarget(String studyHourTarget) {
        this.studyHourTarget = studyHourTarget;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
