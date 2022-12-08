package Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments ")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int ClassKey;
    private String AssessmentTitle;
    private String AssessmentEndDate;

    public Assessment(int classKey, String assessmentTitle, String assessmentEndDate) {
        ClassKey = classKey;
        AssessmentTitle = assessmentTitle;
        AssessmentEndDate = assessmentEndDate;
    }

    public Assessment() {
    }

    public int getClassKey() {
        return ClassKey;
    }

    public void setClassKey(int classKey) {
        ClassKey = classKey;
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
