package com.example.c196_nvrazo.SchoolScheduler.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments ")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int AssessmentID;
    private String AssessmentTitle;
    private String AssessmentEndDate;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentEndDate) {
        AssessmentID = assessmentID;
        AssessmentTitle = assessmentTitle;
        AssessmentEndDate = assessmentEndDate;
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
}
