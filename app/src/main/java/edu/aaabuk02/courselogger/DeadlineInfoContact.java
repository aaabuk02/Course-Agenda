package edu.aaabuk02.courselogger;

public class DeadlineInfoContact {
    private int deadLineContactInfoID;
    private int courseContactID;
    private String deadlineDate;
    private String deadlineInfo;

    public DeadlineInfoContact() {
        deadLineContactInfoID = -1;
    }

    public int getDeadLineContactInfoID() {
        return deadLineContactInfoID;
    }

    public void setDeadLineContactInfoID(int deadLineContactInfoID) {
        this.deadLineContactInfoID = deadLineContactInfoID;
    }

    public int getCourseContactID() {
        return courseContactID;
    }

    public void setCourseContactID(int courseContactID) {
        this.courseContactID = courseContactID;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineInfo() {
        return deadlineInfo;
    }

    public void setDeadlineInfo(String deadlineInfo) {
        this.deadlineInfo = deadlineInfo;
    }


}
