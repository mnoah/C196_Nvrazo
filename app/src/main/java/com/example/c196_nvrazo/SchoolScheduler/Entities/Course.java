package com.example.c196_nvrazo.SchoolScheduler.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int CourseID;
    private String CourseName;
    private String CourseStartDate;
    private String CourseEndDate;
    private String CourseStatus;
    private String CourseInstructorName;
    private String CourseInstructorEmail;
    private String CourseInstructorPhone;
    private String CourseNote;
    private int TermId;

    public Course(int courseID, String courseName, String courseStartDate, String courseEndDate, String courseStatus,
                  String courseInstructorName, String courseInstructorEmail, String courseInstructorPhone, String courseNote, int termId) {
        CourseID = courseID;
        CourseName = courseName;
        CourseStartDate = courseStartDate;
        CourseEndDate = courseEndDate;
        CourseStatus = courseStatus;
        CourseInstructorName = courseInstructorName;
        CourseInstructorEmail = courseInstructorEmail;
        CourseInstructorPhone = courseInstructorPhone;
        CourseNote = courseNote;
        TermId = termId;
    }

    public Course() {
    }

    public int getCourseID() { return CourseID; }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    public String getCourseName() { return CourseName; }

    public void setCourseName(String courseName) { CourseName = courseName; }

    public String getCourseStartDate() {
        return CourseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        CourseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return CourseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        CourseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return CourseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        CourseStatus = courseStatus;
    }

    public String getCourseInstructorName() {
        return CourseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        CourseInstructorName = courseInstructorName;
    }

    public String getCourseInstructorEmail() {
        return CourseInstructorEmail;
    }

    public void setCourseInstructorEmail(String courseInstructorEmail) {
        CourseInstructorEmail = courseInstructorEmail;
    }

    public String getCourseInstructorPhone() {
        return CourseInstructorPhone;
    }

    public void setCourseInstructorPhone(String courseInstructorPhone) {
        CourseInstructorPhone = courseInstructorPhone;
    }

    public String getCourseNote() {
        return CourseNote;
    }

    public void setCourseNote(String courseNote) {
        CourseNote = courseNote;
    }

    public int getTermId() {return TermId;}

    public void setTermId(int termId) { TermId = termId;}
}


