package com.example.c196_nvrazo.SchoolScheduler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196_nvrazo.SchoolScheduler.DAO.AssessmentDAO;
import com.example.c196_nvrazo.SchoolScheduler.DAO.CourseDAO;
import com.example.c196_nvrazo.SchoolScheduler.DAO.TermDAO;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

import java.util.ConcurrentModificationException;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1, exportSchema = false)
public abstract class DBBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();

    public abstract CourseDAO courseDAO();

    public abstract AssessmentDAO assessmentDAO();

    public static volatile DBBuilder INSTANCE;

    static DBBuilder getBD(final Context context){
        if(INSTANCE==null){
            synchronized (DBBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), DBBuilder.class, "SchoolSchedulerDB.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }



}
