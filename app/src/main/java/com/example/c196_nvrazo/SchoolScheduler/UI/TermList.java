package com.example.c196_nvrazo.SchoolScheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
=======
import androidx.recyclerview.widget.RecyclerView;
>>>>>>> parent of db4a735 (Database stuff, Main menu option)

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196_nvrazo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
<<<<<<< HEAD
=======
        RecyclerView recyclerView=findViewById(R.id.TermListRecyclerView);
>>>>>>> parent of db4a735 (Database stuff, Main menu option)
        FloatingActionButton button = findViewById(R.id.TermListAddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermInfo.class);
                startActivity(intent);
            }

        });
    }
}