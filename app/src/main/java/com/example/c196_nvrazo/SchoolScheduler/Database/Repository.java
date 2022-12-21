package com.example.c196_nvrazo.SchoolScheduler.Database;

import android.app.Application;
import com.example.c196_nvrazo.SchoolScheduler.DAO.AssessmentDAO;
import com.example.c196_nvrazo.SchoolScheduler.DAO.CourseDAO;
import com.example.c196_nvrazo.SchoolScheduler.DAO.TermDAO;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CourseDAO mCourseDAO;
    private TermDAO mTermDao;
    private AssessmentDAO mAssessmentDAO;

    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;

    private static int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        DBBuilder db = DBBuilder.getBD(application);
        mCourseDAO = db.courseDAO();
        mTermDao = db.termDAO();
        mAssessmentDAO = db.assessmentDAO();
    }

    //Term list
    public List<Term> getmAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    //Term Insert
    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            mTermDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Term Update
    public void update(Term term) {
        databaseExecutor.execute(() -> {
            mTermDao.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Term Delete
    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            mTermDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Course list
    public List<Course> getmAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    //Course Insert
    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Course Update
    public void update(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Course Delete
    public void delete(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Assessment List
    public List<Assessment> getmAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    //Assessment Insert
    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Assessment Update
    public void update(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Assessment Delete
    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
