package com.example.c196_nvrazo.SchoolScheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;


public class CourseInfo extends AppCompatActivity {

        EditText EditCourseStartDateText;
        EditText EditCourseNameText;
        EditText EditCourseEndDateText;
        EditText EditCourseInstructorNameText;
        EditText EditCourseInstructorEmailText;
        EditText EditCourseInstructorPhoneText;

        String CourseName;
        String CourseStartDate;
        String CourseEndDate;
        String CourseInstructorName;
        String CourseInstructorEmail;
        String CourseInstructorPhone;

        int termId;
        int courseId;
        Term term;
        Course course;
        Repository repository;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_course_info);

            EditCourseNameText= findViewById(R.id.editcoursenametext);
            EditCourseStartDateText = findViewById(R.id.editcoursestartdatetext);
            EditCourseEndDateText = findViewById(R.id.editcourseenddatetext);
            EditCourseInstructorNameText = findViewById(R.id.editcourseinstructornametext);
            EditCourseInstructorPhoneText = findViewById(R.id.editcourseinstructorphonetext);
            EditCourseInstructorEmailText = findViewById(R.id.editcourseinstructoremailtext);


            CourseName = getIntent().getStringExtra("CourseName");
            CourseStartDate = getIntent().getStringExtra("CourseStartDate");
            CourseEndDate = getIntent().getStringExtra("CourseEndDate");

            EditCourseNameText.setText(CourseName);
            EditCourseStartDateText.setText(CourseStartDate);
            EditCourseEndDateText.setText(CourseEndDate);

            termId = getIntent().getIntExtra("TermId", -1);
            courseId = getIntent().getIntExtra("CourseId", -1 );

            repository = new Repository(getApplication());

            //getting and displaying courses for a given term
            RecyclerView recyclerView = findViewById(R.id.assassmentInfoRecyclerView);
            final CourseAdapter courseAdapter = new CourseAdapter(this);
            recyclerView.setAdapter(courseAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            courseAdapter.setCourses(repository.getmAllCourses());

            List<Course> filteredCourses = new ArrayList<>();
            for(Course c : repository.getmAllCourses()){
                if(c.getTermId() == termId)
                    filteredCourses.add(c);
            }

            //Save button setup
            Button saveButton = findViewById(R.id.CourseInfoSaveButton);
            saveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(courseId == -1){
                        course = new Course(0, EditCourseNameText.getText().toString(),
                                EditCourseStartDateText.getText().toString(),
                                EditCourseStartDateText.getText().toString(),
                                );
                        repository.insert(term);
                        //Toast.makeText(this, "Term is saved", Toast.LENGTH_LONG).show();

                    }else{
                        term = new Term(termId, EditTermNameText.getText().toString(),
                                EditTermStartDateText.getText().toString(),
                                EditTermEndDateText.getText().toString());
                        repository.update(term);
                        //Toast.makeText(this, "Term is updated", Toast.LENGTH_LONG).show();

                    }

                }
            });

            Button deleteButton = findViewById(R.id.TermInfoDeleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    term = new Term(termId, EditTermNameText.getText().toString(),
                            EditTermStartDateText.getText().toString(),
                            EditTermEndDateText.getText().toString());
                    repository.delete(term);
                }
            });


            FloatingActionButton floatButton = findViewById(R.id.CourseInfoAddAssessmentButton);
            floatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(com.example.c196_nvrazo.SchoolScheduler.UI.TermInfo.this, CourseInfo.class);
                    intent.putExtra("TermID", termId);
                    startActivity(intent);
                }

            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

    }
}