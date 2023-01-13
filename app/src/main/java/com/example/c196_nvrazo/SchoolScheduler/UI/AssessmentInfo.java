package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class AssessmentInfo extends AppCompatActivity {

    EditText EditAssessmentNameText;
    EditText EditAssessmentEndDateText;

    DatePickerDialog.OnDateSetListener OnAssessmentEndDate;
    final Calendar AssessmentEndDatePicker = Calendar.getInstance();

    String AssessmentName;
    String AssessmentEndDate;

    int courseId;
    int Id;
    Course course;
    Assessment assessment;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String DateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat, Locale.US);

        setContentView(R.layout.activity_assessment_info);

        EditAssessmentNameText= findViewById(R.id.editassessmentnametext);
        EditAssessmentEndDateText = findViewById(R.id.editassessmentenddatetext);

        Id = getIntent().getIntExtra("Id", -1 );
        AssessmentName = getIntent().getStringExtra("Name");
        AssessmentEndDate = getIntent().getStringExtra("EndDate");
        courseId = getIntent().getIntExtra("CourseId", -1);

        EditAssessmentNameText.setText(AssessmentName);
        EditAssessmentEndDateText.setText(AssessmentEndDate);
        repository = new Repository(getApplication());


        //Save button setup
        Button saveButton = findViewById(R.id.AssessmentInfoSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(Id == -1){
                    assessment = new Assessment(0,
                            EditAssessmentNameText.getText().toString(),
                            EditAssessmentEndDateText.getText().toString(),
                            courseId);
                    repository.insert(assessment);
                }else{
                    assessment = new Assessment(Id,
                            EditAssessmentNameText.getText().toString(),
                            EditAssessmentEndDateText.getText().toString(),
                            courseId);
                    repository.update(assessment);
                }
                finish();
            }
        });

        Button deleteButton = findViewById(R.id.AssessmentInfoDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                assessment = new Assessment(Id,
                        EditAssessmentNameText.getText().toString(),
                        EditAssessmentEndDateText.getText().toString(),
                        courseId);
                repository.delete(assessment);
                finish();
            }
        });

        EditAssessmentEndDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;

                String info = EditAssessmentEndDateText.getText().toString();
                try {
                    AssessmentEndDatePicker.setTime(simpleDateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(AssessmentInfo.this,
                        OnAssessmentEndDate, AssessmentEndDatePicker.get(Calendar.YEAR),
                        AssessmentEndDatePicker.get(Calendar.MONTH),
                        AssessmentEndDatePicker.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        OnAssessmentEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                AssessmentEndDatePicker.set(Calendar.YEAR, year);
                AssessmentEndDatePicker.set(Calendar.MONTH, month);
                AssessmentEndDatePicker.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelEnd();
            }
        };
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditAssessmentEndDateText.setText(sdf.format(AssessmentEndDatePicker.getTime()));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessment_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.notify_end:
                String dateFromScreen = EditAssessmentEndDateText.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;

                try {
                    myDate = simpleDateFormat.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger = myDate.getTime();
                Intent intent = new Intent(AssessmentInfo.this, MyReceiver.class);
                intent.putExtra("key", dateFromScreen + " should trigger");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentInfo.this, ++MainActivity.NumberAlrt, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
