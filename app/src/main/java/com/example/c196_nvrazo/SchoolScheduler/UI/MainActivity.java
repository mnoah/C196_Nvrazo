package com.example.c196_nvrazo.SchoolScheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Assessment;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Course;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.EnterButtonMainActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermList.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addData) {
            Term term = new Term(0, "Summer", "1/1/2020", "1/1/2022");
            Repository repository = new Repository(getApplication());
            repository.insert(term);

            Course course = new Course(0, "Math", "9/15/2015", "10/20/2020", "good standing", "Jeff Jeffson", "jeff@School.com", "555-555-225", 2);
            repository.insert(course);


            Assessment assessment = new Assessment(0, "Exam", "12/20/2023", 1);
            repository.insert(assessment);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}