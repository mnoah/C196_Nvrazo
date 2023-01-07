package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.*;
import com.example.c196_nvrazo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermInfo extends AppCompatActivity {

    EditText EditTermStartDateText;
    EditText EditTermNameText;
    EditText EditTermEndDateText;
    String TermName;
    String TermStartDate;
    String TermEndDate;
    int id;
    Term term;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_info);

        EditTermNameText= findViewById(R.id.edittermnametext);
        EditTermStartDateText = findViewById(R.id.edittermstartdatetext);
        EditTermEndDateText = findViewById(R.id.edittermenddatetext);

        TermName = getIntent().getStringExtra("TermName");
        TermStartDate = getIntent().getStringExtra("TermStartDate");
        TermEndDate = getIntent().getStringExtra("TermEndDate");

        EditTermNameText.setText(TermName);
        EditTermStartDateText.setText(TermStartDate);
        EditTermEndDateText.setText(TermEndDate);

        id = getIntent().getIntExtra("TermId", -1);
        repository = new Repository(getApplication());
        Button saveButton = findViewById(R.id.TermInfoSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(id == -1){
                    term = new Term(0, EditTermNameText.getText().toString(),
                            EditTermStartDateText.getText().toString(),
                            EditTermEndDateText.getText().toString());
                    repository.insert(term);
                    //Toast.makeText(this, "Term is saved", Toast.LENGTH_LONG).show();

                }else{
                    term = new Term(id, EditTermNameText.getText().toString(),
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
                term = new Term(id, EditTermNameText.getText().toString(),
                        EditTermStartDateText.getText().toString(),
                        EditTermEndDateText.getText().toString());
                repository.delete(term);
            }
        });


        FloatingActionButton floatButton = findViewById(R.id.TermInfoAddButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermInfo.this, ClassInfo.class);
                intent.putExtra("TermID", id);
                startActivity(intent);
            }

        });
    }
}