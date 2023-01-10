package com.example.c196_nvrazo.SchoolScheduler.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int AssessmentID;
    private String AssessmentTitle;
    private String AssessmentEndDate;
    private int CourseId;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentEndDate, int courseId) {
        AssessmentID = assessmentID;
        AssessmentTitle = assessmentTitle;
        AssessmentEndDate = assessmentEndDate;
        CourseId = courseId;
    }

    public Assessment() {
    }

    public int getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        AssessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return AssessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        AssessmentTitle = assessmentTitle;
    }

    public String getAssessmentEndDate() {
        return AssessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        AssessmentEndDate = assessmentEndDate;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }
}
