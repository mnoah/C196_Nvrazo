package Entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "classes ")
public class Class {
    @PrimaryKey(autoGenerate = true)
    private int ClassKey;
    private String ClassStartDate;
    private String ClassEndDate;
    private String ClassStatus;
    private String ClassInstructorName;
    private String ClassInstructorEmail;
    private String ClassInstructorPhone;

    public Class(int classKey, String classStartDate, String classEndDate, String classStatus,
                 String classInstructorName, String classInstructorEmail, String classInstructorPhone) {
        ClassKey = classKey;
        ClassStartDate = classStartDate;
        ClassEndDate = classEndDate;
        ClassStatus = classStatus;
        ClassInstructorName = classInstructorName;
        ClassInstructorEmail = classInstructorEmail;
        ClassInstructorPhone = classInstructorPhone;
    }

    public Class() {
    }

    public int getClassKey() {
        return ClassKey;
    }

    public void setClassKey(int classKey) {
        ClassKey = classKey;
    }

    public String getClassStartDate() {
        return ClassStartDate;
    }

    public void setClassStartDate(String classStartDate) {
        ClassStartDate = classStartDate;
    }

    public String getClassEndDate() {
        return ClassEndDate;
    }

    public void setClassEndDate(String classEndDate) {
        ClassEndDate = classEndDate;
    }

    public String getClassStatus() {
        return ClassStatus;
    }

    public void setClassStatus(String classStatus) {
        ClassStatus = classStatus;
    }

    public String getClassInstructorName() {
        return ClassInstructorName;
    }

    public void setClassInstructorName(String classInstructorName) {
        ClassInstructorName = classInstructorName;
    }

    public String getClassInstructorEmail() {
        return ClassInstructorEmail;
    }

    public void setClassInstructorEmail(String classInstructorEmail) {
        ClassInstructorEmail = classInstructorEmail;
    }

    public String getClassInstructorPhone() {
        return ClassInstructorPhone;
    }

    public void setClassInstructorPhone(String classInstructorPhone) {
        ClassInstructorPhone = classInstructorPhone;
    }
}
