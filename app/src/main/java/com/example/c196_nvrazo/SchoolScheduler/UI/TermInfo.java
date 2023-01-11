package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.*;
import com.example.c196_nvrazo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TermInfo extends AppCompatActivity {

    EditText EditTermStartDateText;
    EditText EditTermNameText;
    EditText EditTermEndDateText;
    String TermName;
    String TermStartDate;
    String TermEndDate;
    int Id;
    Term term;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_info);

        EditTermNameText = findViewById(R.id.edittermnametext);
        EditTermStartDateText = findViewById(R.id.edittermstartdatetext);
        EditTermEndDateText = findViewById(R.id.edittermenddatetext);

        TermName = getIntent().getStringExtra("Name");
        TermStartDate = getIntent().getStringExtra("StartDate");
        TermEndDate = getIntent().getStringExtra("EndDate");

        EditTermNameText.setText(TermName);
        EditTermStartDateText.setText(TermStartDate);
        EditTermEndDateText.setText(TermEndDate);

        Id = getIntent().getIntExtra("Id", -1);
        repository = new Repository(getApplication());

        //getting and displaying courses for a given term
        RecyclerView recyclerView = findViewById(R.id.CourseInfoRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(repository.getmAllCourses());

        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getmAllCourses()) {
            if (c.getTermId() == Id)
                filteredCourses.add(c);
        }

        courseAdapter.setCourses(filteredCourses);

        //Save button setup
        Button saveButton = findViewById(R.id.TermInfoSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Id == -1) {
                    term = new Term(0, EditTermNameText.getText().toString(),
                            EditTermStartDateText.getText().toString(),
                            EditTermEndDateText.getText().toString());
                    repository.insert(term);


                } else {
                    term = new Term(Id, EditTermNameText.getText().toString(),
                            EditTermStartDateText.getText().toString(),
                            EditTermEndDateText.getText().toString());
                    repository.update(term);


                }

            }
        });

        Button deleteButton = findViewById(R.id.TermInfoDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                term = new Term(Id, EditTermNameText.getText().toString(),
                        EditTermStartDateText.getText().toString(),
                        EditTermEndDateText.getText().toString());
                repository.delete(term);
            }
        });


        FloatingActionButton floatButton = findViewById(R.id.TermInfoAddButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermInfo.this, CourseInfo.class);
                intent.putExtra("TermId", Id);
                startActivity(intent);
            }

        });


    }



    @Override
    protected void onResume(){

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.CourseInfoRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses = new ArrayList<>();
        for (Course c : repository.getmAllCourses()) {
            if (c.getTermId() == Id)
                filteredCourses.add(c);
        }
        courseAdapter.setCourses(filteredCourses);
    }
}