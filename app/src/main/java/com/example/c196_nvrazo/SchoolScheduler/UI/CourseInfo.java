package com.example.c196_nvrazo.SchoolScheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CourseInfo extends AppCompatActivity {

    EditText EditCourseStartDateText;
    EditText EditCourseNameText;
    EditText EditCourseEndDateText;
    EditText EditCourseStatusText;
    EditText EditCourseInstructorNameText;
    EditText EditCourseInstructorEmailText;
    EditText EditCourseInstructorPhoneText;
    EditText EditCourseNoteText;

    Spinner courseStatusSpinner;

    DatePickerDialog.OnDateSetListener OnCourseStartDate;
    final Calendar CourseStartDatePicker = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener OnCourseEndDate;
    final Calendar CourseEndDatePicker = Calendar.getInstance();

    String CourseName;
    String CourseStartDate;
    String CourseEndDate;
    String CourseStatus;
    String CourseInstructorName;
    String CourseInstructorEmail;
    String CourseInstructorPhone;
    String CourseNote;

    int termId;
    int Id;
    Term term;
    Course course;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        String DateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat, Locale.US);

        // The status (in progress, completed, dropped, plan to take)

        ReloadPage();

        repository = new Repository(getApplication());
        List<String> Status = new ArrayList<>();

        Status.add("Select Status");
        Status.add("In Progress");
        Status.add("Completed");
        Status.add("Dropped");
        Status.add("Plan to take");

        String compareValue = CourseStatus;
        Spinner spinner = findViewById(R.id.Course_Status_Spinner);

        ArrayAdapter<String> statusArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Status);
        statusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(statusArrayAdapter);

        if (compareValue != null) {
            int spinnerPosition = statusArrayAdapter.getPosition(compareValue);
            spinner.setSelection(spinnerPosition);
        }



        //getting and displaying assessments for a given term
        RecyclerView recyclerView = findViewById(R.id.assessmentInfoRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessment(repository.getmAssessments());

        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment a : repository.getmAssessments()) {
            if (a.getCourseId() == Id)
                filteredAssessments.add(a);
        }

        //Save button setup
        Button saveButton = findViewById(R.id.CourseInfoSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CourseStatus = spinner.getSelectedItem().toString();
                EditCourseStatusText.setText(CourseStatus);
                if (Id == -1) {
                    course = new Course(0, EditCourseNameText.getText().toString(),
                            EditCourseStartDateText.getText().toString(),
                            EditCourseEndDateText.getText().toString(),
                            EditCourseStatusText.getText().toString(),
                            EditCourseInstructorNameText.getText().toString(),
                            EditCourseInstructorPhoneText.getText().toString(),
                            EditCourseInstructorEmailText.getText().toString(),
                            EditCourseNoteText.getText().toString(),
                            termId);
                    Toast.makeText(CourseInfo.this, EditCourseNameText.getText().toString() + " was created", Toast.LENGTH_LONG).show();

                    repository.insert(course);
                } else {
                    course = new Course(Id, EditCourseNameText.getText().toString(),
                            EditCourseStartDateText.getText().toString(),
                            EditCourseEndDateText.getText().toString(),
                            EditCourseStatusText.getText().toString(),
                            EditCourseInstructorNameText.getText().toString(),
                            EditCourseInstructorPhoneText.getText().toString(),
                            EditCourseInstructorEmailText.getText().toString(),
                            EditCourseNoteText.getText().toString(),
                            termId);
                    repository.update(course);
                    Toast.makeText(CourseInfo.this, CourseName + " was updated", Toast.LENGTH_LONG).show();

                }
                finish();
            }

        });

        Button deleteButton = findViewById(R.id.CourseInfoDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course = new Course(Id, EditCourseNameText.getText().toString(),
                        EditCourseStartDateText.getText().toString(),
                        EditCourseStartDateText.getText().toString(),
                        EditCourseStatusText.getText().toString(),
                        EditCourseInstructorNameText.getText().toString(),
                        EditCourseInstructorPhoneText.getText().toString(),
                        EditCourseInstructorEmailText.getText().toString(),
                        EditCourseNoteText.getText().toString(),
                        termId);
                repository.delete(course);

                Toast.makeText(CourseInfo.this, CourseName + " was deleted", Toast.LENGTH_LONG).show();
                finish();
            }

        });


        FloatingActionButton floatButton = findViewById(R.id.CourseInfoAddAssessmentButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseInfo.this, AssessmentInfo.class);
                intent.putExtra("CourseId", Id);
                startActivity(intent);
            }

        });

        EditCourseStartDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;

                String info = EditCourseStartDateText.getText().toString();
                try {
                    CourseStartDatePicker.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(CourseInfo.this,
                        OnCourseStartDate, CourseStartDatePicker.get(Calendar.YEAR),
                        CourseStartDatePicker.get(Calendar.MONTH),
                        CourseStartDatePicker.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        OnCourseStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                CourseStartDatePicker.set(Calendar.YEAR, year);
                CourseStartDatePicker.set(Calendar.MONTH, month);
                CourseStartDatePicker.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }
        };

        EditCourseEndDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;

                String info = EditCourseEndDateText.getText().toString();
                try {
                    CourseEndDatePicker.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(CourseInfo.this,
                        OnCourseEndDate, CourseEndDatePicker.get(Calendar.YEAR),
                        CourseEndDatePicker.get(Calendar.MONTH),
                        CourseEndDatePicker.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        OnCourseEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                CourseEndDatePicker.set(Calendar.YEAR, year);
                CourseEndDatePicker.set(Calendar.MONTH, month);
                CourseEndDatePicker.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelEnd();
            }
        };

    }


    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditCourseStartDateText.setText(sdf.format(CourseStartDatePicker.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditCourseEndDateText.setText(sdf.format(CourseEndDatePicker.getTime()));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.courses_manu_options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, EditCourseNoteText.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify_start:
                String dateFromScreen = EditCourseStartDateText.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;

                try {
                    myDate = simpleDateFormat.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger = myDate.getTime();
                Intent intent = new Intent(CourseInfo.this, MyReceiver.class);
                intent.putExtra("key", "Start Date: " +dateFromScreen + " should trigger");
                PendingIntent sender = PendingIntent.getBroadcast(CourseInfo.this, ++MainActivity.NumberAlrt, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notify_end:
                 dateFromScreen = EditCourseEndDateText.getText().toString();
                 myFormat = "MM/dd/yy"; //In which you need put here
                 simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                 myDate = null;

                try {
                    myDate = simpleDateFormat.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                 trigger = myDate.getTime();
                 intent = new Intent(CourseInfo.this, MyReceiver.class);
                 intent.putExtra("key", "Start Date: " + dateFromScreen + " should trigger");
                 sender = PendingIntent.getBroadcast(CourseInfo.this, ++MainActivity.NumberAlrt, intent, PendingIntent.FLAG_IMMUTABLE);
                 alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                 alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentInfoRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment a : repository.getmAssessments()) {
            if (a.getCourseId() == Id)
                filteredAssessments.add(a);
        }
        assessmentAdapter.setAssessment(filteredAssessments);
        ReloadPage();


    }

    public void ReloadPage(){
        EditCourseNameText = findViewById(R.id.edit_course_name_text);
        EditCourseStartDateText = findViewById(R.id.edit_course_start_date_text);
        EditCourseEndDateText = findViewById(R.id.edit_course_end_date_text);
        EditCourseStatusText = findViewById(R.id.edit_course_status_text);
        courseStatusSpinner = findViewById(R.id.Course_Status_Spinner);
        EditCourseInstructorNameText = findViewById(R.id.edit_course_instructor_name_text);
        EditCourseInstructorPhoneText = findViewById(R.id.edit_course_instructor_phone_text);
        EditCourseInstructorEmailText = findViewById(R.id.edit_course_instructor_email_text);
        EditCourseNoteText = findViewById(R.id.edit_course_note_text);


        Id = getIntent().getIntExtra("Id", -1);
        CourseName = getIntent().getStringExtra("Name");
        CourseStartDate = getIntent().getStringExtra("StartDate");
        CourseEndDate = getIntent().getStringExtra("EndDate");
        CourseStatus = getIntent().getStringExtra("Status");
        CourseInstructorName = getIntent().getStringExtra("InstructorName");
        CourseInstructorEmail = getIntent().getStringExtra("InstructorEmail");
        CourseInstructorPhone = getIntent().getStringExtra("InstructorPhone");
        CourseNote = getIntent().getStringExtra("Note");
        termId = getIntent().getIntExtra("TermId", -1);


        EditCourseNameText.setText(CourseName);
        EditCourseStartDateText.setText(CourseStartDate);
        EditCourseEndDateText.setText(CourseEndDate);
        EditCourseStatusText.setText(CourseStatus);
        EditCourseInstructorNameText.setText(CourseInstructorName);
        EditCourseInstructorPhoneText.setText(CourseInstructorPhone);
        EditCourseInstructorEmailText.setText(CourseInstructorEmail);
        EditCourseNoteText.setText(CourseNote);
    }
}
