package com.example.c196_nvrazo.SchoolScheduler.Database;

import androidx.room.Database;

import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1)
public class DBBuilder {
}
