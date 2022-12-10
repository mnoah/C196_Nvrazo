package com.example.c196_nvrazo.SchoolScheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;

import java.util.List;


@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course classes);

    @Update
    void update(Course classes);

    @Delete
    void delete(Course classes);

    @Query("SELECT * FROM COURSES ORDER BY COURSEID ASC")
    List<Course> getAllCourses();
}
